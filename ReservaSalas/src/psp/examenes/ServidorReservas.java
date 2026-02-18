package psp.examenes;

/**
 *
 * @author Reis Bernabé
 * @author Vicente Martínez
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.HashMap;

public class ServidorReservas {

    public static final int PUERTO = 4444;

    // Estructuras compartidas
    public static final ArrayList<Sala> salas = new ArrayList<>();
    // clave: código sala, valor: nombre usuario
    public static final HashMap<String, String> reservas = new HashMap<>();
    public static final ArrayList<String> usuariosConectados = new ArrayList<>();

    public static void main(String[] args) {
        inicializarDatosPrueba();

        try (ServerSocket socketServidor = new ServerSocket(PUERTO)) {
            System.out.println("Escuchando: " + socketServidor);
            Socket socketCliente = null;
            // ****************************************************************
            // Hacer el servidor Multihilo
            // ****************************************************************
            while (true) {
                socketCliente = socketServidor.accept();
                System.out.println("Conexión aceptada: " + socketCliente);
                new WorkerReservas(socketCliente).start();
            }
        } catch (IOException e) {
            System.err.println("Error en servidor: " + e.getMessage());
        }
    }

    private static void inicializarDatosPrueba() {
        salas.add(new Sala("A1", "Aula Informática 1", 20));
        salas.add(new Sala("A2", "Aula Informática 2", 25));
        salas.add(new Sala("SALA3", "Sala de Reuniones", 10));
        salas.add(new Sala("LAB1", "Laboratorio Redes", 15));
        salas.add(new Sala("LAB2", "Laboratorio Servidores", 12));
    }
}
