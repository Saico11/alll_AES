public class PriorityQueueLinked<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    
    private Queue<E>[] queues;
    private int priorities;
    
    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int priorities) {
        this.priorities = priorities;
        this.queues = new QueueLink[priorities];
        for (int i = 0; i < priorities; i++) {
            queues[i] = new QueueLink<>();
        }
    }
    
    @Override
    public void enqueue(E x, N pr) {
        int priority = pr instanceof Integer ? (Integer)pr : 0; // Asumimos que pr es convertible a índice
        if (priority < 0 || priority >= priorities) {
            throw new IllegalArgumentException("Invalid priority");
        }
        queues[priority].enqueue(x);
    }
    
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cannot remove from an empty priority queue");
        }
        
        // Buscar la cola de mayor prioridad no vacía
        for (int i = priorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].dequeue();
            }
        }
        
        throw new ExceptionIsEmpty("Cannot remove from an empty priority queue");
    }
    
    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Priority queue is empty");
        }
        
        // Buscar el primer elemento de la cola de mayor prioridad no vacía
        for (int i = priorities - 1; i >= 0; i--) {
            if (!queues[i].isEmpty()) {
                return queues[i].front();
            }
        }
        
        throw new ExceptionIsEmpty("Priority queue is empty");
    }
    
    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Priority queue is empty");
        }
        
        // Buscar el último elemento de la cola de menor prioridad no vacía
        for (int i = 0; i < priorities; i++) {
            if (!queues[i].isEmpty()) {
                return queues[i].back();
            }
        }
        
        throw new ExceptionIsEmpty("Priority queue is empty");
    }
    
    @Override
    public boolean isEmpty() {
        // Verificar si todas las colas están vacías
        for (int i = 0; i < priorities; i++) {
            if (!queues[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PriorityQueueLinked [");
        
        for (int i = priorities - 1; i >= 0; i--) {
            sb.append("\nPriority ").append(i).append(": ").append(queues[i].toString());
        }
        
        sb.append("\n]");
        return sb.toString();
    }
}
