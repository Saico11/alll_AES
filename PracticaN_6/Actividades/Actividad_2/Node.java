// Clase que representa un nodo de la lista enlazada
public class Node<E> {
    private E data;        // Dato almacenado en el nodo
    private Node<E> next;  // Referencia al siguiente nodo
    
    // Constructor que inicializa el nodo con un dato
    public Node(E data) {
        this.data = data;
        this.next = null;  // Inicialmente no tiene siguiente nodo
    }
    
    // Getter para obtener el dato del nodo
    public E getData() {
        return data;
    }
    
    // Getter para obtener el siguiente nodo
    public Node<E> getNext() {
        return next;
    }
    
    // Setter para establecer el siguiente nodo
    public void setNext(Node<E> next) {
        this.next = next;
    }
}
