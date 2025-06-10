
// Implementación de lista enlazada genérica
public class ListLinked<T> {
    // Referencia al primer nodo de la lista
    private Node<T> first;
    
    // Referencia al último nodo de la lista
    private Node<T> last;
    
    // Referencia al nodo actual para iteración
    private Node<T> current;
    
    // Tamaño actual de la lista
    private int size;

    // Clase interna para representar los nodos
    private static class Node<T> {
        // Dato almacenado en el nodo
        T data;
        
        // Referencia al siguiente nodo
        Node<T> next;

        // Constructor del nodo
        Node(T data) {
            // Asigna el dato recibido
            this.data = data;
            // Inicializa next como null
            this.next = null;
        }
    }

    // Constructor de la lista
    public ListLinked() {
        // Inicializa first como null (lista vacía)
        first = null;
        // Inicializa last como null (lista vacía)
        last = null;
        // Inicializa current como null (sin iteración)
        current = null;
        // Inicializa size en 0 (lista vacía)
        size = 0;
    }

    // Método para insertar un elemento al final de la lista
    public void insert(T data) {
        // Crea un nuevo nodo con el dato recibido
        Node<T> newNode = new Node<>(data);
        
        // Si la lista está vacía
        if (first == null) {
            // El nuevo nodo es primero y último
            first = newNode;
            last = newNode;
        } else {
            // Agrega el nuevo nodo después del último
            last.next = newNode;
            // Actualiza last para apuntar al nuevo nodo
            last = newNode;
        }
        // Incrementa el tamaño de la lista
        size++;
    }

    public void insertFirst(T data) {
      Node<T> newNode = new Node <>(data);
      if (first == null) {
        first = newNode;
        last = newNode;
      } else {
        newNode.next = first;
        first = newNode;
      }
      size++;
    }

    // Método para obtener el primer elemento de la lista
    public T getFirst() {
        // Si la lista está vacía, retorna null
        if (first == null) return null;
        // Establece current en el primer nodo
        current = first;
        // Retorna el dato del primer nodo
        return current.data;
    }

    // Método para obtener el siguiente elemento durante la iteración
    public T getNext() {
        // Si current es null o no hay siguiente, retorna null
        if (current == null || current.next == null) return null;
        // Avanza current al siguiente nodo
        current = current.next;
        // Retorna el dato del nuevo current
        return current.data;
    }

    // Método para verificar si un elemento está en la lista
    public boolean contains(T data) {
        // Nodo auxiliar para recorrer la lista
        Node<T> aux = first;
        
        // Recorre la lista
        while (aux != null) {
            // Si encuentra el dato, retorna true
            if (aux.data.equals(data)) {
                return true;
            }
            // Avanza al siguiente nodo
            aux = aux.next;
        }
        // Si no lo encontró, retorna false
        return false;
    }

    // Método para eliminar un elemento específico de la lista
    public void remove(T data) {
        // Si la lista está vacía, no hace nada
        if (first == null) return;

        // Caso especial: eliminar el primer elemento
        if (first.data.equals(data)) {
            // Mueve first al siguiente nodo
            first = first.next;
            // Si first es null, la lista quedó vacía
            if (first == null) last = null;
            // Reduce el tamaño
            size--;
            return;
        }

        // Nodos para recorrer la lista
        Node<T> prev = first;
        Node<T> current = first.next;

        // Busca el elemento a eliminar
        while (current != null) {
            // Si encuentra el dato
            if (current.data.equals(data)) {
                // Salta el nodo a eliminar
                prev.next = current.next;
                // Si era el último, actualiza last
                if (current == last) last = prev;
                // Reduce el tamaño
                size--;
                return;
            }
            // Avanza los punteros
            prev = current;
            current = current.next;
        }
    }

    public void removeFirst() {
      if (first != null) {
        first = first.next;
        if (first == null) last = null;
        size--;
      }
    }

    public boolean isEmpty() {
      return first == null;
    }

    public int size() {
      return size;
    }


    // Método para representar la lista como String
    public String toString() {
        // StringBuilder para construir el resultado
        StringBuilder sb = new StringBuilder("[");
        // Nodo auxiliar para recorrer la lista
        Node<T> aux = first;
        
        // Recorre la lista
        while (aux != null) {
            // Agrega el dato actual
            sb.append(aux.data);
            // Si hay más elementos, agrega separador
            if (aux.next != null) sb.append(", ");
            // Avanza al siguiente nodo
            aux = aux.next;
        }
        // Cierra el corchete
        sb.append("]");
        // Retorna el String construido
        return sb.toString();
    }
}
