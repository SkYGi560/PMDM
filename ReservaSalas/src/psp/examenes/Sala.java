package psp.examenes;

/**
 *
 * @author Reis Bernabé 
 * @author Vicente Martínez
 */
public class Sala {
    private String codigo;
    private String nombre;
    private int aforo;

    public Sala(String codigo, String nombre, int aforo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.aforo = aforo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAforo() {
        return aforo;
    }

    @Override
    public String toString() {
        return codigo + "," + nombre + "," + aforo;
    }
}
