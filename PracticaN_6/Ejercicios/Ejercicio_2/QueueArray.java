public class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int first;  // Índice del primer elemento
    private int last;   // Índice del último elemento
    private int size;   // Cantidad actual de elementos
    private final int capacity; // Capacidad máxima del arreglo

    // Constructor que recibe la capacidad máxima
    @SuppressWarnings("unchecked")
    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
        this.first = 0;
        this.last = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(E x) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        // Aritmética modular para manejar el arreglo circular
        last = (last + 1) % capacity;
        array[last] = x;
        size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Cannot dequeue from an empty queue");
        }
        E removedElement = array[first];
        array[first] = null; // Opcional (para liberar referencia)
        first = (first + 1) % capacity;
        size--;
        return removedElement;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Queue is empty");
        }
        return array[first];
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Queue is empty");
        }
        return array[last];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Método adicional para verificar si la cola está llena
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        int current = first;
        for (int i = 0; i < size; i++) {
            sb.append(array[current]);
            if (i < size - 1) {
                sb.append(", ");
            }
            current = (current + 1) % capacity;
        }
        sb.append("]");
        return sb.toString();
    }
}
