import java.util.Objects;

// Clase que representa una arista en la representacion por lista de aristas
public class EdgeObj<V,E> {
    // Informacion asociada a la arista (peso, etc.)
    protected E info;
    
    // Primer vertice extremo de la arista
    protected VertexObj<V,E> endVertex1;
    
    // Segundo vertice extremo de la arista
    protected VertexObj<V,E> endVertex2;
    
    // Posicion de la arista en la secuencia
    protected int position;

    // Constructor que inicializa la arista
    public EdgeObj(VertexObj<V,E> vert1, VertexObj<V,E> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    // Metodo para comparar aristas por sus vertices extremos
    @Override
    public boolean equals(Object obj) {
        // Verifica si es la misma instancia
        if (this == obj) return true;
        // Verifica si el objeto es nulo o de otra clase
        if (obj == null || getClass() != obj.getClass()) return false;
        // Hace casting a EdgeObj
        EdgeObj<?,?> edgeObj = (EdgeObj<?,?>) obj;
        // Compara ambos vertices extremos (sin importar el orden)
        return (Objects.equals(endVertex1, edgeObj.endVertex1) && 
                Objects.equals(endVertex2, edgeObj.endVertex2)) ||
               (Objects.equals(endVertex1, edgeObj.endVertex2) && 
                Objects.equals(endVertex2, edgeObj.endVertex1));
    }

    // Metodo para generar codigo hash basado en los vertices
    @Override
    public int hashCode() {
        return Objects.hash(endVertex1, endVertex2) + Objects.hash(endVertex2, endVertex1);
    }

    // Representacion en String de la arista
    @Override
    public String toString() {
        return "E[" + endVertex1.info + "-" + endVertex2.info + "]" + 
               (info != null ? "(" + info + ")" : "");
    }
}
