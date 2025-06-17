// clase que representa un registro de estudiante
public class RegistroEstudiante implements Comparable<RegistroEstudiante> {
    // codigo unico del estudiante
    private int codigo;
    // nombre del estudiante
    private String nombre;
    // constructor
    public RegistroEstudiante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
    // obtiene codigo
    public int getCodigo() {
        return codigo;
    }
    // obtiene nombre
    public String getNombre() {
        return nombre;
    }
    // comparacion por codigo
    @Override
    public int compareTo(RegistroEstudiante otro) {
        return Integer.compare(this.codigo, otro.codigo);
    }
    // representacion como "codigo - nombre"
    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}

