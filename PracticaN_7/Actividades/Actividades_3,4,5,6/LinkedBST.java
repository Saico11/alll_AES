// Implementacion de un Arbol Binario de Busqueda (BST) usando nodos enlazados
// Esta clase es generica y requiere que los elementos sean comparables
// E es el tipo de los elementos almacenados en el BST, debe implementar Comparable
public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    
    // Clase interna que representa un nodo del arbol
    // Cada nodo contiene un dato y referencias a sus hijos izquierdo y derecho
    protected class Node {
        public E data;          // El dato almacenado en este nodo
        public Node left;       // Referencia al hijo izquierdo
        public Node right;      // Referencia al hijo derecho
        
        // Constructor para crear un nodo hoja (sin hijos)
        // data: El dato a almacenar en el nodo
        public Node(E data) {
            this(data, null, null);
        }
        
        // Constructor completo para crear un nodo con hijos
        // data: El dato a almacenar
        // left: Hijo izquierdo
        // right: Hijo derecho
        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    private Node root;  // Raiz del arbol

    // Constructor que crea un BST vacio
    public LinkedBST() {
        this.root = null;
    }
    
    // Verifica si el arbol esta vacio
    // Retorna: true si el arbol esta vacio, false en caso contrario
    @Override
    public boolean isEmpty() {
        return root == null;
    }
    
    // Inserta un nuevo elemento en el arbol
    // data: El elemento a insertar
    // Lanza: ItemDuplicated si el elemento ya existe en el arbol
    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }
    
    // Metodo recursivo auxiliar para insertar un elemento
    // node: Nodo actual en la recursion
    // data: Elemento a insertar
    // Retorna: El nodo modificado
    // Lanza: ItemDuplicated si el elemento ya existe
    private Node insertRec(Node node, E data) throws ItemDuplicated {
        // Caso base: encontramos posicion para insertar
        if (node == null) {
            return new Node(data);
        }
        
        // Comparar el nuevo dato con el dato del nodo actual
        int compareResult = data.compareTo(node.data);
        
        // El dato es menor, insertar en subarbol izquierdo
        if (compareResult < 0) {
            node.left = insertRec(node.left, data);
        } 
        // El dato es mayor, insertar en subarbol derecho
        else if (compareResult > 0) {
            node.right = insertRec(node.right, data);
        } 
        // El dato ya existe, lanzar excepcion
        else {
            throw new ItemDuplicated("El elemento ya existe en el arbol");
        }
        
        return node;
    }
    
    // Busca un elemento en el arbol
    // data: Elemento a buscar
    // Retorna: El elemento encontrado
    // Lanza: ItemNotFound si el elemento no existe
    @Override
    public E search(E data) throws ItemNotFound {
        Node result = searchRec(root, data);
        if (result == null) {
            throw new ItemNotFound("Elemento no encontrado en el arbol");
        }
        return result.data;
    }
    
    // Metodo recursivo auxiliar para buscar un elemento
    // node: Nodo actual en la recursion
    // data: Elemento a buscar
    // Retorna: Nodo que contiene el elemento, o null si no se encuentra
    private Node searchRec(Node node, E data) {
        // Caso base: nodo nulo o encontramos el dato
        if (node == null || node.data.equals(data)) {
            return node;
        }
        
        // Si el dato es menor, buscar en subarbol izquierdo
        if (data.compareTo(node.data) < 0) {
            return searchRec(node.left, data);
        } 
        // Si el dato es mayor, buscar en subarbol derecho
        else {
            return searchRec(node.right, data);
        }
    }
    
    // Elimina un elemento del arbol
    // data: Elemento a eliminar
    // Lanza: ExceptionIsEmpty si el arbol esta vacio
    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El arbol esta vacio");
        }
        root = deleteRec(root, data);
    }
    
    // Metodo recursivo auxiliar para eliminar un elemento
    // node: Nodo actual en la recursion
    // data: Elemento a eliminar
    // Retorna: Nodo modificado despues de la eliminacion
    private Node deleteRec(Node node, E data) {
        // Caso base: nodo no encontrado
        if (node == null) {
            return node;
        }
        
        // Comparar el dato a eliminar con el dato del nodo actual
        int compareResult = data.compareTo(node.data);
        
        // Buscar en subarbol izquierdo
        if (compareResult < 0) {
            node.left = deleteRec(node.left, data);
        } 
        // Buscar en subarbol derecho
        else if (compareResult > 0) {
            node.right = deleteRec(node.right, data);
        } 
        // Encontramos el nodo a eliminar
        else {
            // Caso 1: Nodo hoja o con un solo hijo
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            
            // Caso 2: Nodo con dos hijos
            // Reemplazar con el minimo del subarbol derecho
            node.data = findMinNode(node.right).data;
            // Eliminar el nodo minimo del subarbol derecho
            node.right = deleteRec(node.right, node.data);
        }
        
        return node;
    }
    
    // Encuentra el nodo con el valor minimo en un subarbol
    // node: Raiz del subarbol donde buscar
    // Retorna: Nodo con el valor minimo
    private Node findMinNode(Node node) {
        Node current = node;
        // El minimo es el nodo mas a la izquierda
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    // Encuentra el nodo con el valor maximo en un subarbol
    // node: Raiz del subarbol donde buscar
    // Retorna: Nodo con el valor maximo
    private Node findMaxNode(Node node) {
        Node current = node;
        // El maximo es el nodo mas a la derecha
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }
    
    // Realiza un recorrido InOrder (izquierda, raiz, derecha)
    // Retorna: String con los elementos en orden
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderRec(root, sb);
        return sb.toString();
    }
    
    // Metodo recursivo auxiliar para recorrido InOrder
    // node: Nodo actual en la recursion
    // sb: StringBuilder para acumular los resultados
    private void inOrderRec(Node node, StringBuilder sb) {
        if (node != null) {
            inOrderRec(node.left, sb);
            sb.append(node.data).append(" ");
            inOrderRec(node.right, sb);
        }
    }
    
    // Realiza un recorrido PreOrder (raiz, izquierda, derecha)
    // Retorna: String con los elementos en preorden
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderRec(root, sb);
        return sb.toString();
    }
    
    // Metodo recursivo auxiliar para recorrido PreOrder
    // node: Nodo actual en la recursion
    // sb: StringBuilder para acumular los resultados
    private void preOrderRec(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.data).append(" ");
            preOrderRec(node.left, sb);
            preOrderRec(node.right, sb);
        }
    }
    
    // Realiza un recorrido PostOrder (izquierda, derecha, raiz)
    // Retorna: String con los elementos en postorden
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderRec(root, sb);
        return sb.toString();
    }
    
    // Metodo recursivo auxiliar para recorrido PostOrder
    // node: Nodo actual en la recursion
    // sb: StringBuilder para acumular los resultados
    private void postOrderRec(Node node, StringBuilder sb) {
        if (node != null) {
            postOrderRec(node.left, sb);
            postOrderRec(node.right, sb);
            sb.append(node.data).append(" ");
        }
    }
    
    // Retorna una representacion del arbol con todos los recorridos
    @Override
    public String toString() {
        return "InOrder: " + inOrder() + "\nPreOrder: " + preOrder() + "\nPostOrder: " + postOrder();
    }
    
    // Encuentra el elemento minimo en el arbol
    // Retorna: El elemento minimo
    // Lanza: ItemNotFound si el arbol esta vacio
    public E findMin() throws ItemNotFound {
        if (isEmpty()) {
            throw new ItemNotFound("El arbol esta vacio");
        }
        return findMinNode(root).data;
    }
    
    // Encuentra el elemento maximo en el arbol
    // Retorna: El elemento maximo
    // Lanza: ItemNotFound si el arbol esta vacio
    public E findMax() throws ItemNotFound {
        if (isEmpty()) {
            throw new ItemNotFound("El arbol esta vacio");
        }
        return findMaxNode(root).data;
    }
}
