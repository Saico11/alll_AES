import java.util.Queue;          
import java.util.LinkedList; 

// Clase que implementa un Arbol Binario de Busqueda (BST) usando nodos enlazados
// La clase es generica y requiere que los elementos sean comparables
// E es el tipo de los elementos almacenados en el BST, debe implementar Comparable<E>
public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    
    // Clase interna que representa un nodo del arbol binario
    // Cada nodo contiene un dato y referencias a sus hijos izquierdo y derecho
    protected class Node {
        // El dato almacenado en este nodo
        public E data;
        
        // Referencia al hijo izquierdo (subarbol izquierdo)
        public Node left;
        
        // Referencia al hijo derecho (subarbol derecho)
        public Node right;
        
        // Constructor basico que crea un nodo hoja (sin hijos)
        // @param data El elemento a almacenar en el nodo
        public Node(E data) {
            this(data, null, null);
        }
        
        // Constructor completo que crea un nodo con referencias a hijos
        // @param data El elemento a almacenar en el nodo
        // @param left Referencia al hijo izquierdo
        // @param right Referencia al hijo derecho
        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    // Referencia al nodo raiz del arbol
    // Es null cuando el arbol esta vacio
    private Node root;

    // Constructor que inicializa un BST vacio
    // La raiz se establece como null indicando arbol vacio
    public LinkedBST() {
        this.root = null;
    }
    
    // Verifica si el arbol esta vacio
    // @return true si el arbol no tiene nodos, false en caso contrario
    @Override
    public boolean isEmpty() {
        return root == null;
    }
    
    // Inserta un nuevo elemento en el arbol manteniendo la propiedad BST
    // @param data El elemento a insertar
    // @throws ItemDuplicated Si el elemento ya existe en el arbol
    @Override
    public void insert(E data) throws ItemDuplicated {
        // Llamada al metodo recursivo de insercion comenzando desde la raiz
        root = insertRec(root, data);
    }
    
    // Metodo recursivo auxiliar para insertar un nuevo elemento
    // @param node El nodo actual en la recursion
    // @param data El elemento a insertar
    // @return El nodo modificado despues de la insercion
    // @throws ItemDuplicated Si el elemento ya existe en el arbol
    private Node insertRec(Node node, E data) throws ItemDuplicated {
        // Caso base: encontramos la posicion donde insertar (nodo nulo)
        if (node == null) {
            return new Node(data);
        }
        
        // Comparar el nuevo dato con el dato del nodo actual
        int compareResult = data.compareTo(node.data);
        
        // Si el dato es menor, insertar en el subarbol izquierdo
        if (compareResult < 0) {
            node.left = insertRec(node.left, data);
        } 
        // Si el dato es mayor, insertar en el subarbol derecho
        else if (compareResult > 0) {
            node.right = insertRec(node.right, data);
        } 
        // Si el dato es igual, lanzar excepcion (no se permiten duplicados)
        else {
            throw new ItemDuplicated("El elemento ya existe en el arbol");
        }
        
        // Retornar el nodo (posiblemente modificado)
        return node;
    }
    
    // Busca un elemento en el arbol
    // @param data El elemento a buscar
    // @return El elemento encontrado
    // @throws ItemNotFound Si el elemento no existe en el arbol
    @Override
    public E search(E data) throws ItemNotFound {
        // Llamada al metodo recursivo de busqueda
        Node result = searchRec(root, data);
        
        // Si no se encontro el elemento, lanzar excepcion
        if (result == null) {
            throw new ItemNotFound("Elemento no encontrado en el arbol");
        }
        
        // Retornar el dato encontrado
        return result.data;
    }
    
    // Metodo recursivo auxiliar para buscar un elemento
    // @param node El nodo actual en la recursion
    // @param data El elemento a buscar
    // @return El nodo que contiene el elemento, o null si no se encuentra
    private Node searchRec(Node node, E data) {
        // Caso base: nodo nulo o encontramos el dato buscado
        if (node == null || node.data.equals(data)) {
            return node;
        }
        
        // Si el dato es menor, buscar en el subarbol izquierdo
        if (data.compareTo(node.data) < 0) {
            return searchRec(node.left, data);
        } 
        // Si el dato es mayor, buscar en el subarbol derecho
        else {
            return searchRec(node.right, data);
        }
    }
    
    // Elimina un elemento del arbol
    // @param data El elemento a eliminar
    // @throws ExceptionIsEmpty Si el arbol esta vacio
    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        // Verificar si el arbol esta vacio
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El arbol esta vacio");
        }
        
        // Llamada al metodo recursivo de eliminacion
        root = deleteRec(root, data);
    }
    
    // Metodo recursivo auxiliar para eliminar un elemento
    // @param node El nodo actual en la recursion
    // @param data El elemento a eliminar
    // @return El nodo modificado despues de la eliminacion
    private Node deleteRec(Node node, E data) {
        // Caso base: nodo nulo (elemento no encontrado)
        if (node == null) {
            return node;
        }
        
        // Comparar el dato a eliminar con el dato del nodo actual
        int compareResult = data.compareTo(node.data);
        
        // Si el dato es menor, buscar en el subarbol izquierdo
        if (compareResult < 0) {
            node.left = deleteRec(node.left, data);
        } 
        // Si el dato es mayor, buscar en el subarbol derecho
        else if (compareResult > 0) {
            node.right = deleteRec(node.right, data);
        } 
        // Si encontramos el nodo a eliminar
        else {
            // Caso 1: Nodo hoja o con un solo hijo
            if (node.left == null) {
                return node.right; // Reemplazar por el hijo derecho (puede ser null)
            } else if (node.right == null) {
                return node.left;  // Reemplazar por el hijo izquierdo
            }
            
            // Caso 2: Nodo con dos hijos
            try {
                // Encontrar el minimo valor en el subarbol derecho (sucesor inorden)
                E minValue = findMinNode(node.right);
                // Copiar el valor minimo al nodo actual
                node.data = minValue;
                // Eliminar el nodo que tenia el valor minimo
                node.right = deleteRec(node.right, minValue);
            } catch (ItemNotFound e) {
                // Este caso no deberia ocurrir porque sabemos que hay subarbol derecho
                return node;
            }
        }
        
        return node;
    }

    // Metodo privado para encontrar el nodo con el valor minimo en un subarbol
    // @param node La raiz del subarbol donde buscar
    // @return El valor minimo encontrado
    // @throws ItemNotFound Si el subarbol esta vacio
    private E findMinNode(Node node) throws ItemNotFound {
        // Verificar si el subarbol esta vacio
        if (node == null) {
            throw new ItemNotFound("El subarbol esta vacio");
        }
        
        // El minimo siempre esta en el nodo mas a la izquierda
        Node current = node;
        E minValue = current.data;
        
        // Recorrer hacia la izquierda hasta encontrar el minimo
        while (current.left != null) {
            current = current.left;
            minValue = current.data;
        }
        
        // Validar que el valor minimo existe usando search()
        return search(minValue);
    } 
    
    // Metodo privado para encontrar el nodo con el valor maximo en un subarbol
    // @param node La raiz del subarbol donde buscar
    // @return El valor maximo encontrado
    // @throws ItemNotFound Si el subarbol esta vacio
    private E findMaxNode(Node node) throws ItemNotFound {
        // Verificar si el subarbol esta vacio
        if (node == null) {
            throw new ItemNotFound("El subarbol esta vacio");
        }
        
        // El maximo siempre esta en el nodo mas a la derecha
        Node current = node;
        E maxValue = current.data;
        
        // Recorrer hacia la derecha hasta encontrar el maximo
        while (current.right != null) {
            current = current.right;
            maxValue = current.data;
        }
        
        // Validar que el valor maximo existe usando search()
        return search(maxValue);
    }
    
    // Realiza un recorrido InOrder (izquierda, raiz, derecha)
    // @return String con los elementos ordenados
    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderRec(root, sb);
        return sb.toString();
    }
    
    // Metodo recursivo auxiliar para recorrido InOrder
    // @param node El nodo actual en la recursion
    // @param sb StringBuilder para acumular los resultados
    private void inOrderRec(Node node, StringBuilder sb) {
        if (node != null) {
            // Recorrer subarbol izquierdo
            inOrderRec(node.left, sb);
            // Visitar nodo actual
            sb.append(node.data).append(" ");
            // Recorrer subarbol derecho
            inOrderRec(node.right, sb);
        }
    }
    
    // Realiza un recorrido PreOrder (raiz, izquierda, derecha)
    // @return String con los elementos en preorden
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderRec(root, sb);
        return sb.toString();
    }
    
    // Metodo recursivo auxiliar para recorrido PreOrder
    // @param node El nodo actual en la recursion
    // @param sb StringBuilder para acumular los resultados
    private void preOrderRec(Node node, StringBuilder sb) {
        if (node != null) {
            // Visitar nodo actual
            sb.append(node.data).append(" ");
            // Recorrer subarbol izquierdo
            preOrderRec(node.left, sb);
            // Recorrer subarbol derecho
            preOrderRec(node.right, sb);
        }
    }
    
    // Realiza un recorrido PostOrder (izquierda, derecha, raiz)
    // @return String con los elementos en postorden
    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderRec(root, sb);
        return sb.toString();
    }
    
    // Metodo recursivo auxiliar para recorrido PostOrder
    // @param node El nodo actual en la recursion
    // @param sb StringBuilder para acumular los resultados
    private void postOrderRec(Node node, StringBuilder sb) {
        if (node != null) {
            // Recorrer subarbol izquierdo
            postOrderRec(node.left, sb);
            // Recorrer subarbol derecho
            postOrderRec(node.right, sb);
            // Visitar nodo actual
            sb.append(node.data).append(" ");
        }
    }
    
    // Retorna una representacion del arbol con todos los recorridos
    // @return String con los recorridos inorden, preorden y postorden
    @Override
    public String toString() {
        return "InOrder: " + inOrder() + "\nPreOrder: " + preOrder() + "\nPostOrder: " + postOrder();
    }
    
    // Encuentra el elemento minimo en el arbol
    // @return El elemento minimo
    // @throws ItemNotFound Si el arbol esta vacio
    public E findMin() throws ItemNotFound {
        if (isEmpty()) {
            throw new ItemNotFound("El arbol esta vacio");
        }
        return findMinNode(root);
    }
    
    // Encuentra el elemento maximo en el arbol
    // @return El elemento maximo
    // @throws ItemNotFound Si el arbol esta vacio
    public E findMax() throws ItemNotFound {
        if (isEmpty()) {
            throw new ItemNotFound("El arbol esta vacio");
        }
        return findMaxNode(root);
    }
    
    // EJERCICIOS IMPLEMENTADOS
    
    // 1a. Metodo para eliminar todos los nodos del BST
    // @throws ExceptionIsEmpty Si el arbol ya esta vacio
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("El arbol esta vacio");
        }
        // Simplemente establecer la raiz como null
        root = null;
    }
    
    // 1b. Metodo para contar todos los nodos del BST
    // @return El numero total de nodos en el arbol
    public int countAllNodes() {
        return countAllNodesRec(root);
    }
    
    // Metodo recursivo auxiliar para contar todos los nodos
    // @param node El nodo actual en la recursion
    // @return El conteo de nodos en el subarbol
    private int countAllNodesRec(Node node) {
        // Caso base: nodo nulo no cuenta
        if (node == null) {
            return 0;
        }
        // Contar este nodo + nodos en subarboles izquierdo y derecho
        return 1 + countAllNodesRec(node.left) + countAllNodesRec(node.right);
    }
    
    // 1c. Metodo para contar nodos no-hoja del BST
    // @return El numero de nodos internos (no hojas)
    public int countNodes() {
        return countNodesRec(root);
    }
    
    // Metodo recursivo auxiliar para contar nodos no-hoja
    // @param node El nodo actual en la recursion
    // @return El conteo de nodos internos en el subarbol
    private int countNodesRec(Node node) {
        // Caso base: nodo nulo o nodo hoja no cuenta
        if (node == null || (node.left == null && node.right == null)) {
            return 0;
        }
        // Contar este nodo (es interno) + nodos internos en subarboles
        return 1 + countNodesRec(node.left) + countNodesRec(node.right);
    }
    
    // 1d. Metodo para obtener altura de un subarbol con raiz x (iterativo)
    // @param x El elemento que es raiz del subarbol
    // @return La altura del subarbol, -1 si no existe
    // @throws ItemNotFound Si el elemento no existe en el arbol
    public int height(E x) throws ItemNotFound {
        // Buscar el nodo que contiene el elemento x
        Node node = searchRec(root, x);
        
        // Si no se encontro, retornar -1
        if (node == null) {
            return -1;
        }
        
        // Usar BFS para calcular la altura
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int height = -1;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            
            // Procesar todos los nodos del nivel actual
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                
                // Agregar hijos a la cola para el siguiente nivel
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        
        return height;
    }
    
    // 1e. Metodo para obtener amplitud del arbol
    // @param level El nivel del cual se quiere la amplitud
    // @return El numero de nodos en el nivel especificado
    public int amplitude(int level) {
        if (root == null) {
            return 0;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int currentLevel = 0;
        int maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Si encontramos el nivel buscado, retornar su tamaño
            if (currentLevel == level) {
                return levelSize;
            }
            
            // Actualizar la amplitud maxima encontrada
            if (levelSize > maxWidth) {
                maxWidth = levelSize;
            }
            
            // Procesar todos los nodos del nivel actual
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                
                // Agregar hijos a la cola para el siguiente nivel
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            
            currentLevel++;
        }
        
        // Si no se encontro el nivel, retornar la amplitud maxima
        return maxWidth;
    }
    
    // 2a. Metodo para calcular el area del BST
    // El area se define como: (numero de hojas) * (altura del arbol)
    // @return El area calculada del arbol
    public int areaBST() {
        if (root == null) {
            return 0;
        }
        
        // Calcular la altura del arbol completo
        int height = 0;
        try {
            height = height(root.data);
        } catch (ItemNotFound e) {
            height = 0; // No deberia ocurrir
        }
        
        // Contar el numero de hojas
        int leafCount = countLeaves();
        
        // Calcular el area
        return height * leafCount;
    }
    
    // Metodo auxiliar para contar hojas
    // @return El numero de nodos hoja en el arbol
    private int countLeaves() {
        return countLeavesRec(root);
    }
    
    // Metodo recursivo auxiliar para contar hojas
    // @param node El nodo actual en la recursion
    // @return El numero de hojas en el subarbol
    private int countLeavesRec(Node node) {
        if (node == null) {
            return 0;
        }
        // Un nodo es hoja si no tiene hijos
        if (node.left == null && node.right == null) {
            return 1;
        }
        // Sumar hojas de ambos subarboles
        return countLeavesRec(node.left) + countLeavesRec(node.right);
    }
    
    // 2b. Metodo para dibujar el BST
    // @return String con representacion grafica del arbol
    public String drawBST() {
        StringBuilder sb = new StringBuilder();
        drawBSTRec(root, 0, sb);
        return sb.toString();
    }
    
    // Metodo recursivo auxiliar para dibujar el BST
    // @param node El nodo actual en la recursion
    // @param level El nivel actual de profundidad
    // @param sb StringBuilder para acumular la representacion
    private void drawBSTRec(Node node, int level, StringBuilder sb) {
        if (node != null) {
            // Primero el subarbol derecho (para que se vea a la derecha en la salida)
            drawBSTRec(node.right, level + 1, sb);
            
            // Sangria segun el nivel
            for (int i = 0; i < level; i++) {
                sb.append("    ");
            }
            // Agregar el dato del nodo
            sb.append(node.data).append("\n");
            
            // Luego el subarbol izquierdo
            drawBSTRec(node.left, level + 1, sb);
        }
    }
    
    // 3. Metodo para representacion parentetica
    // @return String con representacion parentetica con sangria
    public String parenthesize() {
        StringBuilder sb = new StringBuilder();
        parenthesizeRec(root, 0, sb);
        return sb.toString();
    }
    
    // Metodo recursivo auxiliar para representacion parentetica
    // @param node El nodo actual en la recursion
    // @param level El nivel actual de profundidad
    // @param sb StringBuilder para acumular la representacion
    private void parenthesizeRec(Node node, int level, StringBuilder sb) {
        if (node != null) {
            // Sangria segun el nivel
            for (int i = 0; i < level; i++) {
                sb.append("    ");
            }
            // Agregar el dato del nodo
            sb.append(node.data).append("\n");
            
            // Si tiene hijos, agregar parentesis
            if (node.left != null || node.right != null) {
                sb.append("(\n");
                // Procesar hijos con mayor nivel de sangria
                parenthesizeRec(node.left, level + 1, sb);
                parenthesizeRec(node.right, level + 1, sb);
                // Cerrar parentesis
                for (int i = 0; i < level; i++) {
                    sb.append("    ");
                }
                sb.append(")\n");
            }
        }
    }
}
