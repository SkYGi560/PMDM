package psp.examenes;

/**
 *
 * @author Reis Bernabé
 * @author Vicente Martínez
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerReservas extends Thread {

    private final Socket socketCliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    String salidaPantalla;
    String[] entradas;
    Boolean conexionActiva;
    Estados estadoActual;
    String usuarioActual;

    private enum Estados {
        INICIO, AUTENTICADO, FIN
    }

    public WorkerReservas(Socket socket) {
        this.socketCliente = socket;
        conexionActiva = true;
    }

    // ****************************************************************
    // Crea el/los constructores que necesites
    // ****************************************************************
    // ****************************************************************
    // Termina de definir el método run 
    // ****************************************************************
    @Override
    public void run() {
        try {
            entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())), true);

            protocolo();
            System.out.println("El usuario se desconecta de la aplicación.");

        } catch (IOException e) {
            System.err.println("Error en worker: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
    }

    // ****************************************************************
    // Completar el método protocolo y los métodos correspondientes a cada uno de los comandos
    // Puedes modificar los parámetros y el valor de retorno de todos los módulos.
    // ****************************************************************
    private void protocolo() {
        String comando;
        estadoActual = Estados.INICIO;

        try {
            do {
                entradas = entrada.readLine().split("\\|");
                comando = entradas[0];
                switch (estadoActual) {
                    case INICIO -> {
                        switch (comando) {
                            case "LIST_SALAS" ->
                                manejarListSalas();
                            case "LIST_RESERVAS" ->
                                manejarListReservas();
                            case "LOGIN" ->
                                manejarLogin();
                            default ->
                                salida.println("UNEXPECTED_CMD");
                        }
                    }
                    case AUTENTICADO -> {
                        switch (comando) {
                            case "LOGIN" -> {
                                System.out.println("Comando no esperado. El usuario ya está autenticado.");
                                salida.println("UNEXPECTED_CMD");
                            }
                            case "LIST_SALAS" ->
                                manejarListSalas();
                            case "LIST_RESERVAS" ->
                                manejarListReservas();
                            case "RESERVAR" ->
                                manejarReservar();
                            case "CANCELAR" ->
                                manejarCancelar();
                            case "EXIT", "SALIR" ->
                                manejarSalir();
                            default ->
                                salida.println("UNEXPECTED_CMD");
                        }
                    }
                }
            } while (conexionActiva);
        } catch (IOException ex) {
            System.getLogger(WorkerReservas.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private void manejarLogin() {
        System.out.println("--> Cliente: LOGIN|" + entradas[1]);
        usuarioActual = entradas[1];
        estadoActual = Estados.AUTENTICADO;
        salida.println("OK");
        System.out.println("El usuario " + entradas[1] + " ha entrado en el sistema.");
        System.out.println("Transición: INICIO → AUTENTICADO");
    }

    /* LIST_SALAS */
    private void manejarListSalas() {
        salidaPantalla = "SALAS";
        for (var sala : ServidorReservas.salas) {
            salidaPantalla += "|" + sala.toString();
        }
        salida.println(salidaPantalla);
        System.out.println("--> Cliente: LIST_SALAS");
        System.out.println("Se envía el listado de " + ServidorReservas.salas.size() + " salas al usuario.");
    }

    /* LIST_RESERVAS */
    private void manejarListReservas() {
        System.out.println("--> Cliente: LIST_RESERVAS");
        String salidaConsola = "";
        for (var reserva : ServidorReservas.reservas.entrySet()) {
            salida.println("RESERVAS|" + reserva.getKey() + "," + reserva.getValue());
            salidaConsola += reserva.getKey() + " (" + reserva.getValue() + "),";
        }
        salida.println("RESERVAS|NONE");

        System.out.println("Se envía el listado de reservas: " + salidaConsola);
    }

    /* RESERVAR|usuario|codigoSala */
    private void manejarReservar() {
        System.out.println("--> Cliente: RESERVAR|" + entradas[1] + "|" + entradas[2]);
        String nombreUsuario = entradas[1];
        String codigoSala = entradas[2];
        Boolean disponible = false;
        for (Sala sala : ServidorReservas.salas) {
            if (sala.getCodigo().equals(codigoSala) && !ServidorReservas.reservas.containsKey(codigoSala)) {
                disponible = true;
            }
        }
        if (disponible) {
            ServidorReservas.reservas.put(codigoSala, nombreUsuario);
            System.out.println("La sala " + entradas[2] + " ha sido reservada por el usuario " + entradas[1] + ".");
            salida.println("OK");
        } else {
            salida.println("KO");
            System.out.println("No se puede ocupar la sala " + entradas[2] + " para " + entradas[1] + "(ocupada o inexistente)");
        }

    }

    /* CANCELAR|usuario|codigoSala */
    private void manejarCancelar() {
        System.out.println("--> Cliente: CANCELAR|" + entradas[1] + "|" + entradas[2]);
        String nombreUsuario = entradas[1];
        String codigoSala = entradas[2];
        Boolean disponible = false;
        for (String reserva : ServidorReservas.reservas.keySet()) {
            if (reserva.equals(codigoSala)) {
                disponible = true;
            }
        }
        if (disponible) {
            ServidorReservas.reservas.remove(codigoSala);
            salida.println("OK");
            System.out.println("La reserva de la sala " + entradas[2] + " del usuario " + entradas[1] + " ha sido eliminada.");
        } else {
            salida.println("KO");
            System.out.println("No existe reserva de la sala " + entradas[2] + " para el usuario " + entradas[1] + ".");
        }

    }

    /* SALIR / EXIT */
    private void manejarSalir() {
        conexionActiva = false;
        System.out.println("--> Cliente: EXIT");
        estadoActual = Estados.FIN;
    }

    private void cerrarRecursos() {
        try {
            if (salida != null) {
                salida.close();
            }
            if (entrada != null) {
                entrada.close();
            }
            if (socketCliente != null && !socketCliente.isClosed()) {
                socketCliente.close();
            }
            System.out.println("Conexión cerrada.");
        } catch (IOException e) {
            System.err.println("Error cerrando recursos del cliente: " + e.getMessage());
        }
    }
}
