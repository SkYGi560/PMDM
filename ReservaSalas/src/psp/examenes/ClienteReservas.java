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
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Reis
 * @author Vicente
 */
public class ClienteReservas {

    private static final String HOST = "localhost";
    private static final int PUERTO = 4444;

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    private Scanner teclado;
    private String usuarioActual;

    public static void main(String[] args) {
        ClienteReservas cliente = new ClienteReservas();
        cliente.iniciar();
        cliente.protocolo();
        cliente.cerrarRecursos();
    }

    private void iniciar() {
        try {
            socket = new Socket(HOST, PUERTO);
            entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream())), true);
            teclado = new Scanner(System.in);
            System.out.println("Conectado al servidor de reservas en " + HOST + ":" + PUERTO);
        } catch (UnknownHostException e) {
            System.err.println("No se puede resolver el host: " + HOST);
        } catch (IOException e) {
            System.err.println("Error de E/S en el cliente: " + e.getMessage());
        }
    }

    private void protocolo() {

        boolean salir = false;

        try {
            while (!salir) {
                mostrarMenu();
                int opcion = leerOpcion();
                switch (opcion) {
                    case 1 ->
                        comandoLoginEstados();
                    case 2 ->
                        comandoListSalas();
                    case 3 ->
                        comandoListReservas();
                    case 4 ->
                        comandoReservar();
                    case 5 ->
                        comandoCancelar();
                    case 14 ->
                        comandoReservarEstados();
                    case 15 ->
                        comandoCancelarEstados();

                    case 6 -> {
                        salir = true;
                        comandoSalir();
                    }
                    default ->
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
            System.out.println("Gracias por usar el sistema.");
        } catch (IOException e) {
            System.err.println("Error de E/S en el cliente: " + e.getMessage());
        }
    }

    private void mostrarMenu() {
        System.out.println("========================================");
        System.out.println(" SISTEMA DE GESTIÓN DE RESERVAS DE SALAS");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Selecciona una opción:");
        System.out.println("1. Login (c/estados)");
        System.out.println("2. Listar salas");
        System.out.println("3. Listar reservas");
        System.out.println("4. Reservar una sala");
        System.out.println("5. Cancelar reserva");
        System.out.println("6. Salir");
        System.out.println("14. Reservar una sala (c/estados)");
        System.out.println("15. Cancelar reserva (c/estados)");

        System.out.println();
        System.out.print("Introduce tu opción: ");
    }

    private int leerOpcion() {
        int opcion;
        try {
            String linea = teclado.nextLine();
            opcion = Integer.parseInt(linea.trim());
        } catch (NumberFormatException e) {
            opcion = -1;
        }
        return opcion;
    }

    /* ======================
       COMANDOS CLIENTE
     ====================== */
    private void comandoLoginEstados() throws IOException {
        System.out.print("Introduce tu nombre de usuario: ");
        usuarioActual = teclado.nextLine();
        salida.println("LOGIN|" + usuarioActual);
        if (entrada.readLine().equals("OK")) {
            System.out.println("Usuario identificado correctamente.");
        } else {
            System.out.println("No se ha podido iniciar sesión. Prueba con otro nombre.");
        }
    }

    private void comandoListSalas() throws IOException {
        System.out.println("Introduce comando: LIST_SALAS");
        salida.println("LIST_SALAS");
        System.out.println("** Salas disponibles **");
        String[] entradas = entrada.readLine().split("\\|");
        String[] salidaPantalla;
        for (int i = 1; i < entradas.length; i++) {
            salidaPantalla = entradas[i].split(",");
            System.out.println(salidaPantalla[0] + " - " + salidaPantalla[1] + " (Aforo: " + salidaPantalla[2] + ")");
        }
    }

    private void comandoListReservas() throws IOException {
        System.out.println("Introduce comando: LIST_RESERVAS");
        salida.println("LIST_RESERVAS");
        String entradaSingleLine = entrada.readLine();
        System.out.println(entradaSingleLine);
        String[] entradas;
        String[] salidaPantalla;
        Boolean hayEntradas = !entradaSingleLine.equals("RESERVAS|NONE");

        System.out.println("** Reservas activas **");
        if (hayEntradas) {
            do {
                if(entradaSingleLine.equals("RESERVAS|NONE")){
                    break;
                }
                entradas = entradaSingleLine.split("\\|");
                for (int i = 1; i < entradas.length; i++) {
                    salidaPantalla = entradas[i].split(",");
                    if (salidaPantalla.length > 1) {
                        System.out.println(salidaPantalla[0] + " - " + salidaPantalla[1]);
                    }
                }
                entradaSingleLine = entrada.readLine();
            } while (true);
        } else {
            System.out.println("(No hay reservas activas)");
        }
    }

    private void comandoReservar() throws IOException {
        System.out.println("Introduce comando: RESERVAR");
        System.out.println("Indica el código de la sala que quieres reservar: ");
        String codigoSala = teclado.nextLine();
        System.out.println("Indica el nombre del usuario que quiere realizar la reserva: ");
        String nombreUsuario = teclado.nextLine();

        salida.println("RESERVAR|" + nombreUsuario + "|" + codigoSala);

        if (entrada.readLine().equals("OK")) {
            System.out.println("Reserva realizada correctamente.");
        } else {
            System.out.println("No se ha podido realizar la reserva (sala no existe u ocupada).");
        }
    }

    private void comandoReservarEstados() throws IOException {
        System.out.println("Introduce comando: RESERVAR");
        System.out.println("Indica el código de la sala que quieres reservar: ");
        String codigoSala = teclado.nextLine();

        salida.println("RESERVAR|" + usuarioActual + "|" + codigoSala);

        String entradaConsola = entrada.readLine();

        if (entradaConsola.equals("OK")) {
            System.out.println("Reserva realizada correctamente.");
        } else {
            System.out.println("No se ha podido realizar la reserva (sala no existe u ocupada).");
        }
    }

    private void comandoCancelar() throws IOException {
        System.out.println("Introduce comando: CANCELAR");
        System.out.println("Indica el código de la sala que quieres cancelar: ");
        String codigoSala = teclado.nextLine();
        System.out.println("Indica el nombre del usuario que quiere cancelar la reserva: ");
        String nombreUsuario = teclado.nextLine();

        salida.println("CANCELAR|" + nombreUsuario + "|" + codigoSala);

        if (entrada.readLine().equals("OK")) {
            System.out.println("La reserva de la sala " + codigoSala + " se ha cancelado correctamente.");
        } else {
            System.out.println("No tienes ninguna reserva activa sobre la sala " + codigoSala + ".");
        }
    }

    private void comandoCancelarEstados() throws IOException {
        System.out.println("Introduce comando: CANCELAR");
        System.out.println("Indica el código de la sala que quieres cancelar: ");
        String codigoSala = teclado.nextLine();

        salida.println("CANCELAR|" + usuarioActual + "|" + codigoSala);

        if (entrada.readLine().equals("OK")) {
            System.out.println("La reserva de la sala " + codigoSala + " se ha cancelado correctamente.");
        } else {
            System.out.println("No tienes ninguna reserva activa sobre la sala " + codigoSala + ".");
        }
    }

    private void comandoSalir() throws IOException {
        System.out.println("Introduce comando: EXIT");
        System.out.println("Sesión finalizada. Gracias por usar el sistema de reservas.");
    }

    /* ======================
       CIERRE DE RECURSOS
     ====================== */
    private void cerrarRecursos() {
        try {
            if (salida != null) {
                salida.close();
            }
            if (entrada != null) {
                entrada.close();
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (teclado != null) {
                teclado.close();
            }
        } catch (IOException e) {
            System.err.println("Error cerrando recursos del cliente: " + e.getMessage());
        }
    }
}
