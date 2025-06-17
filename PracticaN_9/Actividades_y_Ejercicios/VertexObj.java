import java.util.Objects;

// Clase que representa un vertice en la representacion por lista de aristas
public class VertexObj<V,E> {
    // Informacion almacenada en el vertice
    protected V info;
    
    // Posicion del vertice en la secuencia
    protected int position;

    // Constructor que inicializa el vertice
    public VertexObj(V info, int position) {
        this.info = info;
        this.position = position;
    }

    // Metodo para comparar vertices por su informacion
    @Override
    public boolean equals(Object obj) {
        // Verifica si es la misma instancia
        if (this == obj) return true;
        // Verifica si el objeto es nulo o de otra clase
        if (obj == null || getClass() != obj.getClass()) return false;
        // Hace casting a VertexObj
        VertexObj<?,?> vertexObj = (VertexObj<?,?>) obj;
        // Compara la informacion de los vertices
        return Objects.equals(info, vertexObj.info);
    }

    // Metodo para generar codigo hash basado en la informacion
    @Override
    public int hashCode() {
        return Objects.hash(info);
    }

    // Representacion en String del vertice
    @Override
    public String toString() {
        return "V{" + info + "}";
    }
}
