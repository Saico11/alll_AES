
// Interface que define las operaciones basicas de una Cola (Queue)
// Utiliza genericos (<E>) para trabajar con cualquier tipo de dato
public interface Queue<E> {
    // Agrega un elemento al final de la cola
    void enqueue(E x);
    
    // Elimina y retorna el elemento del frente de la cola
    // Lanza una excepcion si la cola esta vacia
    E dequeue() throws ExceptionIsEmpty;
    
    // Retorna el elemento del frente sin eliminarlo
    // Lanza una excepcion si la cola esta vacia
    E front() throws ExceptionIsEmpty;
    
    // Retorna el elemento del final sin eliminarlo
    // Lanza una excepcion si la cola esta vacia
    E back() throws ExceptionIsEmpty;
    
    // Verifica si la cola esta vacia
    boolean isEmpty();
}
