// Implementacion de una cola usando lista enlazada
public class QueueLink<E> implements Queue<E> {
    private Node<E> first;  // Referencia al primer nodo (frente de la cola)
    private Node<E> last;   // Referencia al ultimo nodo (final de la cola)
    
    // Constructor que inicializa una cola vacia
    public QueueLink() {
        this.first = null;
        this.last = null;
    }
    
    // Agrega un elemento al final de la cola
    public void enqueue(E x) {
        Node<E> aux = new Node<E>(x);  // Crea un nuevo nodo
        
        if (this.isEmpty()) {
            this.first = aux;  // Si esta vacia, el nuevo nodo es el primero
        } else {
            this.last.setNext(aux);  // Enlaza el nuevo nodo al final
        }
        this.last = aux;  // Actualiza la referencia al ultimo nodo
    }
    
    // Elimina y retorna el elemento del frente de la cola
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cannot dequeue from an empty queue");
        }
        E data = first.getData();  // Obtiene el dato del frente
        first = first.getNext();   // Avanza al siguiente nodo
        
        if (first == null) {
            last = null;  // Si la cola queda vacia, actualiza last
        }
        return data;  // Retorna el dato eliminado
    }
    
    // Retorna el elemento del final sin eliminarlo
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Queue is empty");
        }
        return last.getData();  // Retorna el dato del ultimo nodo
    }
    
    // Retorna el elemento del frente sin eliminarlo
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Queue is empty");
        }
        return first.getData();  // Retorna el dato del primer nodo
    }
    
    // Verifica si la cola esta vacia
    public boolean isEmpty() {
        return first == null;  // La cola esta vacia si first es null
    }
    
    // Representacion en String de la cola
    public String toString() {
        if (isEmpty()) {
            return "Queue is empty";
        }
        
        StringBuilder sb = new StringBuilder();
        Node<E> current = first;
        
        // Recorre todos los nodos desde el primero al ultimo
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(" -> ");  // Separador entre elementos
            }
            current = current.getNext();
        }
        return sb.toString();  // Retorna la cadena construida
    }
}
