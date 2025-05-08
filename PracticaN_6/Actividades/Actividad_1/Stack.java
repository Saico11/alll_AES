// Interface que define las operaciones basicas de una pila (Stack)
public interface Stack<E> {
    // Agrega un elemento a la pila
    void push(E x);
    
    // Elimina y retorna el elemento en el tope de la pila
    E pop() throws ExceptionIsEmpty;
    
    // Retorna el elemento en el tope sin eliminarlo
    E top() throws ExceptionIsEmpty;
    
    // Verifica si la pila esta vacia
    boolean isEmpty();
}
