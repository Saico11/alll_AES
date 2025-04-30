// Clase principal que implementa un gestor de tareas usando lista enlazada
// T es el tipo generico que puede ser String, Tarea u otro objeto
public class GestorDeTareas<T> {
    // Punteros al inicio y final de la lista
    private Node<T> head; // Primer nodo
    private Node<T> tail; // Ultimo nodo
    private int size;     // Cantidad de elementos

    // Constructor inicializa una lista vacia
    public GestorDeTareas() {
        head = null;
        tail = null;
        size = 0;
    }

    // Agrega una tarea al final de la lista
    public void agregarTarea(T tarea) {
        Node<T> newNode = new Node<>(tarea);
        
        // Si la lista esta vacía, el nuevo nodo es head y tail
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            // Si no esta vacía, se agrega después del tail actual
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Elimina una tarea si existe en la lista
    public boolean eliminarTarea(T tarea) {
        // Lista vacaa, nada que eliminar
        if (head == null) return false;

        // Caso especial: eliminar el primer nodo
        if (head.data.equals(tarea)) {
            head = head.next;
            // Si era el unico nodo, actualizar tail
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }

        // Buscar el nodo a eliminar
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(tarea)) {
            current = current.next;
        }

        // Si encontro el nodo, eliminarlo
        if (current.next != null) {
            // Si es el ultimo nodo, actualizar tail
            if (current.next == tail) {
                tail = current;
            }
            current.next = current.next.next;
            size--;
            return true;
        }
        return false; // No se encontro el elemento
    }

    // Verifica si una tarea existe en la lista
    public boolean contieneTarea(T tarea) {
        Node<T> current = head;
        // Recorrer toda la lista buscando el elemento
        while (current != null) {
            if (current.data.equals(tarea)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Imprime todas las tareas de la lista
    public void imprimirTareas() {
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // Devuelve la cantidad de tareas en la lista
    public int contarTareas() {
        return size;
    }

    // Obtiene la tarea con mayor prioridad (solo para objetos Tarea)
    public T obtenerTareaMasPrioritaria() {
        if (head == null) return null;
        
        // Verificar que los datos son de tipo Tarea
        if (!(head.data instanceof Tarea)) {
            throw new UnsupportedOperationException("Este método solo funciona con objetos de tipo Tarea");
        }

        Node<T> current = head;
        T maxPriorityTask = current.data;
        int maxPriority = ((Tarea) current.data).getPrioridad();

        // Buscar la tarea con mayor prioridad
        while (current != null) {
            Tarea tarea = (Tarea) current.data;
            if (tarea.getPrioridad() > maxPriority) {
                maxPriority = tarea.getPrioridad();
                maxPriorityTask = current.data;
            }
            current = current.next;
        }
        return maxPriorityTask;
    }

    // Invierte el orden de la lista enlazada
    public void invertirTareas() {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;
        tail = head; // El tail será el antiguo head

        // Recorrer la lista cambiando las direcciones de los nodos
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev; // El nuevo head es el último nodo
    }

    // Metodos auxiliares para los ejercicios
    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
        // Recalcular tail y size cuando se cambia el head
        this.size = 0;
        this.tail = null;
        Node<T> current = head;
        while (current != null) {
            this.size++;
            this.tail = current;
            current = current.next;
        }
    }
}
