// Clase que implementa una cola de prioridad usando lista enlazada ordenada
class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    
    // Clase interna para encapsular dato y prioridad
    class EntryNode {
        E data;
        N priority;
        
        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }
    
    private Node<EntryNode> first;
    private Node<EntryNode> last;
    
    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }
    
    // Metodo para encolar un elemento con prioridad
    public void enqueue(E x, N pr) {
        EntryNode newEntry = new EntryNode(x, pr);
        Node<EntryNode> newNode = new Node<>(newEntry);
        
        // Caso 1: Lista vacia
        if (isEmpty()) {
            first = newNode;
            last = newNode;
            return;
        }
        
        // Caso 2: Nueva prioridad es mayor que la primera (insertar al inicio)
        if (pr.compareTo(first.getData().priority) > 0) {
            newNode.setNext(first);
            first = newNode;
            return;
        }
        
        // Caso 3: Buscar posicion adecuada para mantener orden descendente
        Node<EntryNode> current = first;
        Node<EntryNode> previous = null;
        
        while (current != null && pr.compareTo(current.getData().priority) <= 0) {
            previous = current;
            current = current.getNext();
        }
        
        // Insertar el nuevo nodo en la posicion encontrada
        newNode.setNext(current);
        previous.setNext(newNode);
        
        // Actualizar last si se inserta al final
        if (current == null) {
            last = newNode;
        }
    }
    
    // Metodo para desencolar el elemento con mayor prioridad
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cannot remove from an empty queue");
        }
        
        E aux = this.first.getData().data;
        this.first = this.first.getNext();
        
        if (this.first == null) {
            this.last = null;
        }
        
        return aux;
    }
    
    // Metodo para obtener el elemento con mayor prioridad (frente)
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Queue is empty");
        }
        return this.first.getData().data;
    }
    
    // Metodo para obtener el elemento con menor prioridad (final)
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Queue is empty");
        }
        return this.last.getData().data;
    }
    
    // Metodo para verificar si la cola esta vacia
    public boolean isEmpty() {
        return this.first == null;
    }
    
    // Metodo para representar la cola como String
    public String toString() {
        if (isEmpty()) {
            return "PriorityQueue is empty";
        }
        
        StringBuilder sb = new StringBuilder();
        Node<EntryNode> current = first;
        
        while (current != null) {
            sb.append("Data: ").append(current.getData().data)
              .append(" (Priority: ").append(current.getData().priority).append(")");
            
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            
            current = current.getNext();
        }
        
        return sb.toString();
    }
}
