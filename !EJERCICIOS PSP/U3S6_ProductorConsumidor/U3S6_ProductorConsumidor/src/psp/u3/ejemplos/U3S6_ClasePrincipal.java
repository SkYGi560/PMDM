
package psp.u3.ejemplos;

public class U3S6_ClasePrincipal {

    public static void main(String[] args) {

        U3A6_ClaseCompartida objetoCompartido = new U3A6_ClaseCompartida();
        U3A6_Productor productor = new U3A6_Productor(objetoCompartido);
        U3A6_Consumidor consumidor = new U3A6_Consumidor(objetoCompartido);
        productor.start();
        consumidor.start();

        // No es obligatorio, pero en ocasiones puede interesar que la ClasePrincipal
        // espere a que acaben los hilos
        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException ex) {
            System.err.println("Se ha producido un error mientras se esperaba a que el productor o el consumidor realizasen su tarea");
        }
        
        // Acciones a realizar una vez hayan acabado el productor y el consumidor
        
    }

}
