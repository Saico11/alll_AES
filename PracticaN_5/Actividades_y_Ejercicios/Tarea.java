// Clase que representa una tarea con titulo y prioridad
// Se usa para demostrar el funcionamiento con objetos complejos
public class Tarea {
    // Atributos de la tarea
    private String titulo;
    private int prioridad;

    // Constructor que inicializa la tarea con titulo y prioridad
    public Tarea(String titulo, int prioridad) {
        this.titulo = titulo;
        this.prioridad = prioridad;
    }

    // Getter para obtener el titulo
    public String getTitulo() {
        return titulo;
    }

    // Getter para obtener la prioridad
    public int getPrioridad() {
        return prioridad;
    }

    // Metodo para representar la tarea como String
    @Override
    public String toString() {
        return "Tarea{" + "titulo='" + titulo + '\'' + ", prioridad=" + prioridad + '}';
    }
}
