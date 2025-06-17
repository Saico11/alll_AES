// clase que implementa un B-Tree generico
public class BTree<E extends Comparable<E>> {
    private BNode<E> root;      // raiz del arbol
    private int order;          // orden (maximo de hijos)
    private boolean up;         // bandera para control de subida (split)
    private BNode<E> nDes;      // nodo derecho resultante en split

    // constructor: crea un B-Tree vacio de orden dado
    public BTree(int order) {
        this.order = order;
        this.root = null;
    }
    
    // verifica si el arbol esta vacio
    public boolean isEmpty() {
        return this.root == null;
    }
    
    // inserta una clave en el arbol
    public void insert(E key) {
        up = false;
        E median;
        median = push(this.root, key);
        // si hubo split en raiz
        if (up) {
            BNode<E> pnew = new BNode<>(this.order);
            pnew.count = 1;
            pnew.keys[0] = median;           // la clave mediana
            pnew.childs[0] = this.root;      // hijo izquierdo
            pnew.childs[1] = nDes;           // hijo derecho
            this.root = pnew;                // nuevo root
        }
    }
    
    // metodo recursivo que propaga la insercion y maneja splits
    private E push(BNode<E> current, E key) {
        int pos[];
        pos = new int[1];
        E median;
        // si llegamos a hoja null, sube la clave
        if (current == null) {
            up = true;
            nDes = null;
            return key;
        }
        // busca dentro del nodo
        int idx = current.searchNode(key);
        if (idx >= 0) {
            up = false;    // clave duplicada, no inserta
            return null;
        }
        int childIndex = -idx - 1;
        // baja al hijo correspondiente
        median = push(current.childs[childIndex], key);
        if (up) {
            // si el nodo esta lleno, lo divide
            if (current.nodeFull(this.order)) {
                median = divideNode(current, median, childIndex);
            } else {
                // si no, inserta sin dividir
                putNode(current, median, nDes, childIndex);
                up = false;
            }
        }
        return median;
    }
    
    // inserta clave y puntero derecho en posicion k del nodo actual
    private void putNode(BNode<E> current, E key, BNode<E> rd, int k) {
        // desplaza claves e hijos para hacer espacio
        for (int i = current.count - 1; i >= k; i--) {
            current.keys[i + 1] = current.keys[i];
            current.childs[i + 2] = current.childs[i + 1];
        }
        current.keys[k] = key;         // coloca la nueva clave
        current.childs[k + 1] = rd;    // coloca el nuevo hijo derecho
        current.count++;               // incrementa contador
    }
    
   // divide el nodo actual en dos, retorna la mediana
private E divideNode(BNode<E> current, E key, int k) {
    // guarda el subarbol derecho que vino de la llamada recursiva
    BNode<E> rightSubtree = nDes;
    // maximo de claves en un nodo
    int maxKeys = this.order - 1;
    // indice de la mediana: floor((maxKeys)/2)
    int mid = maxKeys / 2;

    // crea el nodo derecho del split
    BNode<E> rightNode = new BNode<>(this.order);

    // copia las claves posteriores a mid desde current a rightNode
    // current.count == maxKeys en el caso de overflow
    for (int i = mid + 1; i < current.count; i++) {
        rightNode.keys[i - (mid + 1)] = current.keys[i];
    }
    // copia los hijos correspondientes (hay current.count+1 punteros)
    for (int i = mid + 1; i <= current.count; i++) {
        rightNode.childs[i - (mid + 1)] = current.childs[i];
    }

    // ajusta conteos
    rightNode.count = current.count - mid - 1;
    current.count   = mid;

    // inserta la clave subida en el nodo que corresponda
    if (k <= mid) {
        // si la posicion de insercion va a la izquierda
        putNode(current, key, rightSubtree, k);
    } else {
        // si va al nodo derecho, ajustamos el indice k
        putNode(rightNode, key, rightSubtree, k - (mid + 1));
    }

    // preparamos devolver la clave mediana
    nDes = rightNode;                 // este es el nuevo hijo derecho
    return current.keys[mid];         // promueve la mediana
}


    // *Ejercicio V.1* - busca una clave en el arbol
    public boolean search(E key) {
        return searchRec(this.root, key);
    }
    private boolean searchRec(BNode<E> node, E key) {
        if (node == null) return false;
        int idx = node.searchNode(key);
        if (idx >= 0) {
            System.out.println(key + " se encuentra en el nodo " + node.id + " en posicion " + idx);
            return true;
        }
        int child = -idx - 1;
        return searchRec(node.childs[child], key);
    }
    
    // *Ejercicio V.2* - elimina una clave del arbol
    public void remove(E key) {
        if (this.root == null) return;
        removeRec(this.root, key);
        // si raiz queda sin claves y tiene un hijo, sube ese hijo
        if (this.root.count == 0 && this.root.childs[0] != null) {
            this.root = this.root.childs[0];
        }
    }
    private void removeRec(BNode<E> node, E key) {
        int idx = node.searchNode(key);
        if (idx >= 0) {
            // caso 1: clave en nodo interno
            if (node.childs[idx] != null) {
                // reemplaza por antecesor
                BNode<E> pred = node.childs[idx];
                while (pred.childs[pred.count] != null) {
                    pred = pred.childs[pred.count];
                }
                E predKey = pred.keys[pred.count - 1];
                node.keys[idx] = predKey;
                removeRec(node.childs[idx], predKey);
            } else {
                // hoja: borra directamente
                for (int i = idx; i < node.count - 1; i++) {
                    node.keys[i] = node.keys[i + 1];
                }
                node.count--;
            }
        } else {
            // caso 2: clave no esta aqui -> baja al hijo
            int child = -idx - 1;
            BNode<E> c = node.childs[child];
            if (c == null) return;  // no encontrada
            removeRec(c, key);
            // chequea subarbol con pocas claves
            int minChildren = (this.order + 1) / 2;
            int minKeys = minChildren - 1;
            if (c.count < minKeys) {
                // intenta redistribuir con hermano izq
                BNode<E> left = (child > 0) ? node.childs[child - 1] : null;
                BNode<E> right = (child < node.count) ? node.childs[child + 1] : null;
                // redistribuir con izquierdo
                if (left != null && left.count > minKeys) {
                    // mueve clave padre al subarbol deficitario
                    for (int i = c.count; i > 0; i--) {
                        c.keys[i] = c.keys[i - 1];
                    }
                    c.keys[0] = node.keys[child - 1];
                    c.count++;
                    node.keys[child - 1] = left.keys[left.count - 1];
                    left.count--;
                }
                // redistribuir con derecho
                else if (right != null && right.count > minKeys) {
                    c.keys[c.count] = node.keys[child];
                    c.count++;
                    node.keys[child] = right.keys[0];
                    for (int i = 0; i < right.count - 1; i++) {
                        right.keys[i] = right.keys[i + 1];
                    }
                    right.count--;
                }
                // fusion con izquierdo
                else if (left != null) {
                    // mueve clave padre y todo el derecho a izq
                    left.keys[left.count] = node.keys[child - 1];
                    int pos = left.count + 1;
                    for (int i = 0; i < c.count; i++) {
                        left.keys[pos + i] = c.keys[i];
                    }
                    left.count += 1 + c.count;
                    // corrige enlaces
                    for (int i = child - 1; i < node.count - 1; i++) {
                        node.keys[i] = node.keys[i + 1];
                        node.childs[i + 1] = node.childs[i + 2];
                    }
                    node.count--;
                }
                // fusion con derecho
                else if (right != null) {
                    c.keys[c.count] = node.keys[child];
                    c.count++;
                    for (int i = 0; i < right.count; i++) {
                        c.keys[c.count + i] = right.keys[i];
                    }
                    c.count += right.count;
                    // corrige enlaces
                    for (int i = child; i < node.count - 1; i++) {
                        node.keys[i] = node.keys[i + 1];
                        node.childs[i + 1] = node.childs[i + 2];
                    }
                    node.count--;
                }
            }
        }
    }
    
    // presenta todo el arbol en una cadena
    @Override
    public String toString() {
        if (isEmpty()) return "BTree is empty...";
        return writeTree(this.root);
    }
    // recorrido recursivo en preorden para armar la cadena
    private String writeTree(BNode<E> current) {
        if (current == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(current.toString()).append("\n");
        for (int i = 0; i <= current.count; i++) {
            sb.append(writeTree(current.childs[i]));
        }
        return sb.toString();
    }
    
    // *Ejercicio V.3* - construir arbol desde archivo (sin importar librerias)
    // aqui solo mostramos firma; la implementacion real requeriria lectura de archivo
    public static <T extends Comparable<T>> BTree<T> building_Btree(String filename) throws Exception {
        // lectura del archivo, parseo de lineas y creacion de nodos segun estructura
        // si no cumple propiedades lanza excepcion
        return null; // placeholder
    }
}

