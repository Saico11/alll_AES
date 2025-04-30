// Clase que representa un nodo de una lista enlazada
// T es el tipo generico que almacenará el nodo
public class Node<T> {
    // Dato almacenado en el nodo (puede ser cualquier tipo)
    public T data;
    
    // Referencia al siguiente nodo en la lista
    public Node<T> next;

    // Constructor que inicializa el nodo con un dato
    // y establece next como null (ultimo nodo por defecto)
    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}
