public class StackLink<E> implements Stack<E> {
    private Node<E> top; // Nodo superior de la pila
    
    // Clase Node para la lista enlazada
    private static class Node<E> {
        private E data;
        private Node<E> next;
        
        public Node(E data) {
            this.data = data;
            this.next = null;
        }
        
        public E getData() {
            return data;
        }
        
        public Node<E> getNext() {
            return next;
        }
        
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
    
    public StackLink() {
        this.top = null;
    }
    
    @Override
    public void push(E x) {
        Node<E> newNode = new Node<>(x);
        newNode.setNext(top);
        top = newNode;
    }
    
    @Override
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cannot pop from an empty stack");
        }
        E data = top.getData();
        top = top.getNext();
        return data;
    }
    
    @Override
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cannot get top from an empty stack");
        }
        return top.getData();
    }
    
    @Override
    public boolean isEmpty() {
        return top == null;
    }
    
    @Override
    public String toString() {
        if (isEmpty()) {
            return "Stack is empty";
        }
        
        StringBuilder sb = new StringBuilder();
        Node<E> current = top;
        sb.append("Top -> ");
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }
}
