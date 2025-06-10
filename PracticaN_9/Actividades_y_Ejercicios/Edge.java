
// Clase que representa una arista en el grafo
public class Edge<E> {
    // Referencia al vértice destino de la arista
    public Vertex<E> refDest;
    
    // Peso de la arista (-1 indica que no tiene peso)
    public int weight;

    // Constructor para arista sin peso
    public Edge(Vertex<E> refDest) {
        // Llama al constructor completo con peso -1
        this(refDest, -1);
    }

    // Constructor completo que recibe vértice destino y peso
    public Edge(Vertex<E> refDest, int weight) {
        // Asigna el vértice destino
        this.refDest = refDest;
        // Asigna el peso
        this.weight = weight;
    }

    // Método para comparar si dos aristas son iguales
    public boolean equals(Object o) {
        // Verifica si el objeto es una instancia de Edge
        if (o instanceof Edge<?>) {
            // Hace un casting seguro a Edge<E>
            Edge<E> e = (Edge<E>) o;
            // Compara los vértices destino
            return this.refDest.equals(e.refDest);
        }
        // Si no es una Edge, retorna false
        return false;
    }

    // Método para representar la arista como String
    public String toString() {
        // Si tiene peso, lo muestra entre corchetes
        if (this.weight > -1) {
            return refDest.getData() + " [" + this.weight + "], ";
        } else {
            // Si no tiene peso, solo muestra el destino
            return refDest.getData() + ", ";
        }
    }
}
