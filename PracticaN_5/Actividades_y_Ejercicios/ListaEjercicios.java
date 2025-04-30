// Clase con los metodos para resolver los ejercicios de la practica
public class ListaEjercicios {

    // Ejercicio 1: Busca un elemento en una lista
    public static <T> boolean buscarElemento(Node<T> head, T valor) {
        Node<T> current = head;
        // Recorrer la lista buscando el valor
        while (current != null) {
            if (current.data.equals(valor)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Ejercicio 2: Invierte una lista y devuelve la nueva cabeza
    public static <T> Node<T> invertirLista(Node<T> head) {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = null;

        // Cambiar las direcciones de los nodos
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev; // prev es la nueva cabeza
    }

    // Ejercicio 3: Inserta un nodo al final de la lista
    public static <T> Node<T> insertarAlFinal(Node<T> head, T valor) {
        Node<T> newNode = new Node<>(valor);
        // Si la lista esta vacia, el nuevo nodo es la cabeza
        if (head == null) {
            return newNode;
        }

        // Buscar el ultimo nodo
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        // Insertar después del ultimo nodo
        current.next = newNode;
        return head;
    }

    // Ejercicio 4: Cuenta los nodos de la lista
    public static <T> int contarNodos(Node<T> head) {
        int count = 0;
        Node<T> current = head;
        // Recorrer y contar todos los nodos
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Ejercicio 5: Compara si dos listas son iguales
    public static <T> boolean sonIguales(Node<T> lista1, Node<T> lista2) {
        Node<T> current1 = lista1;
        Node<T> current2 = lista2;

        // Comparar nodo por nodo
        while (current1 != null && current2 != null) {
            if (!current1.data.equals(current2.data)) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        // Ambas listas deben llegar al final al mismo tiempo
        return current1 == null && current2 == null;
    }

    // Ejercicio 6: Concatena dos listas
    public static <T> Node<T> concatenarListas(Node<T> lista1, Node<T> lista2) {
        // Si lista1 es vacía, devolver lista2
        if (lista1 == null) return lista2;
        // Si lista2 es vacía, devolver lista1
        if (lista2 == null) return lista1;

        // Buscar el final de la primera lista
        Node<T> current = lista1;
        while (current.next != null) {
            current = current.next;
        }
        // Enlazar con la segunda lista
        current.next = lista2;
        return lista1;
    }
}
