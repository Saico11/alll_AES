
// Clase que representa un vértice en el grafo
public class Vertex<E> {
    // Campo privado para almacenar el dato del vértice
    private E data;
    
    // Lista de aristas adyacentes a este vértice
    // Es protected para que GraphLink pueda acceder directamente
    protected ListLinked<Edge<E>> listAdj;

    // Constructor que recibe el dato a almacenar en el vértice
    public Vertex(E data) {
        // Asigna el dato recibido al campo data
        this.data = data;
        // Inicializa la lista de aristas adyacentes
        this.listAdj = new ListLinked<Edge<E>>();
    }

    // Método getter para obtener el dato del vértice
    public E getData() {
        // Retorna el valor del campo data
        return data;
    }

    // Método para comparar si dos vértices son iguales
    public boolean equals(Object o) {
        // Verifica si el objeto es una instancia de Vertex
        if (o instanceof Vertex<?>) {
            // Hace un casting seguro del objeto a Vertex<E>
            Vertex<E> v = (Vertex<E>) o;
            // Compara los datos de los vértices usando equals
            return this.data.equals(v.data);
        }
        // Si no es un Vertex, retorna false
        return false;
    }

    // Método para representar el vértice como String
    public String toString() {
        // Retorna el dato del vértice seguido de sus aristas adyacentes
        return this.data + " --> " + this.listAdj.toString() + "\n";
    }
}
