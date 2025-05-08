// Implementacion de Stack usando un arreglo como estructura subyacente
public class StackArray<E> implements Stack<E> {
    private E[] array;    // Arreglo para almacenar los elementos
    private int tope;     // Indice que apunta al elemento en el tope
    
    // Constructor que recibe el tamano maximo de la pila
    public StackArray(int n) {
        this.array = (E[]) new Object[n];  // Creacion de arreglo generico
        tope = -1;  // -1 indica pila vacia
    }
    
    // Agrega un elemento a la pila
    public void push(E x) {
        if (isFull()) {
            throw new IllegalStateException("Stack lleno: no se puede agregar");
        }
        array[++tope] = x;  // Incrementa tope y luego asigna el elemento
    }
    
    // Elimina y retorna el elemento del tope
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Stack vacio: no se puede extraer");
        }
        return array[tope--];  // Retorna el elemento y luego decrementa tope
    }
    
    // Retorna el elemento del tope sin eliminarlo
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("Stack vacio: no hay elementos");
        }
        return array[tope];
    }
    
    // Verifica si la pila esta vacia
    public boolean isEmpty() {
        return this.tope == -1;
    }
    
    // Verifica si la pila esta llena (solo para implementacion con arreglo)
    public boolean isFull() {
        return tope == array.length - 1;
    }
    
    // Representacion en String de la pila (del tope al fondo)
    public String toString() {
        if (isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = tope; i >= 0; i--) {
            sb.append(array[i]);
            if (i > 0) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
