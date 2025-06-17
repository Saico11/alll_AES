// clase que implementa un B-Tree generico
public class BTree<E extends Comparable<E>> {
    // raiz del arbol
    private BNode<E> root;
    // orden (maximo de hijos)
    private int order;
    // bandera para control de splits
    private boolean up;
    // nodo derecho resultante en divisiones
    private BNode<E> nDes;

    // constructor: crea un arbol vacio de orden dado
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
        E median = push(this.root, key);
        // si se produjo un split en la raiz
        if (up) {
            BNode<E> newRoot = new BNode<>(this.order);
            newRoot.count = 1;
            newRoot.keys[0] = median;
            newRoot.childs[0] = this.root;
            newRoot.childs[1] = nDes;
            this.root = newRoot;
        }
    }

    // metodo recursivo para insertar y manejar splits
    private E push(BNode<E> node, E key) {
        // si llegamos a una hoja vacia, subimos la clave
        if (node == null) {
            up = true;
            nDes = null;
            return key;
        }
        // buscamos la posicion dentro del nodo
        int idx = node.searchNode(key);
        if (idx >= 0) {
            // clave duplicada, no insertar
            up = false;
            return null;
        }
        int childIndex = -idx - 1;
        // descendemos al hijo correspondiente
        E median = push(node.childs[childIndex], key);
        if (up) {
            // si el nodo esta lleno, lo dividimos
            if (node.nodeFull(this.order)) {
                median = divideNode(node, median, childIndex);
            } else {
                // si no esta lleno, insertamos directo
                putNode(node, median, nDes, childIndex);
                up = false;
            }
        }
        return median;
    }

    // inserta clave y puntero derecho en posicion k del nodo
    private void putNode(BNode<E> node, E key, BNode<E> rd, int k) {
        // desplazamos claves e hijos para hacer espacio
        for (int i = node.count - 1; i >= k; i--) {
            node.keys[i + 1] = node.keys[i];
            node.childs[i + 2] = node.childs[i + 1];
        }
        // insertamos la nueva clave y su hijo derecho
        node.keys[k] = key;
        node.childs[k + 1] = rd;
        node.count++;
    }

    // divide el nodo actual en dos y retorna la clave mediana
    private E divideNode(BNode<E> current, E key, int k) {
        BNode<E> rightSubtree = nDes;
        int maxKeys = this.order - 1;
        int mid = maxKeys / 2;
        // creamos el nodo derecho del split
        BNode<E> rightNode = new BNode<>(this.order);
        // copiamos las claves posteriores a la mediana
        for (int i = mid + 1; i < current.count; i++) {
            rightNode.keys[i - (mid + 1)] = current.keys[i];
        }
        // copiamos los hijos correspondientes
        for (int i = mid + 1; i <= current.count; i++) {
            rightNode.childs[i - (mid + 1)] = current.childs[i];
        }
        // ajustamos el conteo de claves
        rightNode.count = current.count - mid - 1;
        current.count   = mid;
        // insertamos la clave subida en el lugar correcto
        if (k <= mid) {
            putNode(current, key, rightSubtree, k);
        } else {
            putNode(rightNode, key, rightSubtree, k - (mid + 1));
        }
        // preparamos el nodo derecho para la llamada externa
        nDes = rightNode;
        // retornamos la mediana para subirla
        return current.keys[mid];
    }

    // convierte todo el arbol a string (preorden)
    @Override
    public String toString() {
        if (isEmpty()) {
            return "BTree vacio...";
        }
        return writeTree(this.root);
    }

    // metodo recursivo para recorrer el arbol en preorden
    private String writeTree(BNode<E> node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(node.toString()).append("\n");
        for (int i = 0; i <= node.count; i++) {
            sb.append(writeTree(node.childs[i]));
        }
        return sb.toString();
    }

    // 01. Metodo search para encontrar una clave en el arbol
    public boolean search(E key) {
        // si la raiz es null, arbol vacio -> no encontrado
        if (this.root == null) {
            return false;
        }
        return searchRec(this.root, key);
    }

    // metodo recursivo de soporte para search
    private boolean searchRec(BNode<E> node, E key) {
        // buscamos dentro del nodo
        int idx = node.searchNode(key);
        if (idx >= 0) {
            // si idx>=0, la clave esta en este nodo
            System.out.println(
                key + " se encuentra en el nodo " + node.id + " en la posicion " + idx
            );
            return true;
        }
        // calculamos el hijo al cual descender
        int child = -idx - 1;
        if (node.childs[child] == null) {
            // no hay mas hijos, no existe la clave
            return false;
        }
        // descendemos recursivamente
        return searchRec(node.childs[child], key);
    }

    // 02. Metodo remove para eliminar una clave del arbol
    public void remove(E key) {
        // si arbol vacio, nada que hacer
        if (this.root == null) {
            return;
        }
        // llamada recursiva de eliminacion
        removeRec(this.root, key);
        // si la raiz queda sin claves y tiene un solo hijo, promovemos ese hijo
        if (this.root.count == 0 && this.root.childs[0] != null) {
            this.root = this.root.childs[0];
        }
    }

    // metodo recursivo de soporte para remove
    private void removeRec(BNode<E> node, E key) {
        // buscamos la clave en el nodo actual
        int idx = node.searchNode(key);
        if (idx >= 0) {
            // CASO 1: clave en nodo interno o hoja
            if (node.childs[idx] != null) {
                // nodo interno: reemplazar con antecesor
                BNode<E> pred = node.childs[idx];
                while (pred.childs[pred.count] != null) {
                    pred = pred.childs[pred.count];
                }
                E predKey = pred.keys[pred.count - 1];
                node.keys[idx] = predKey;
                removeRec(node.childs[idx], predKey);
            } else {
                // nodo hoja: desplazar claves hacia la izquierda
                for (int i = idx; i < node.count - 1; i++) {
                    node.keys[i] = node.keys[i + 1];
                }
                node.count--;
            }
        } else {
            // CASO 2: clave no esta en este nodo -> descendemos
            int child = -idx - 1;
            BNode<E> c = node.childs[child];
            if (c == null) {
                // subarbol vacio, no existe la clave
                return;
            }
            // eliminacion recursiva en el hijo
            removeRec(c, key);
            // comprobamos si el subarbol quedo con pocas claves
            int minChildren = (this.order + 1) / 2;
            int minKeys = minChildren - 1;
            if (c.count < minKeys) {
                // intentamos redistribuir o fusionar
                BNode<E> left  = (child > 0)             ? node.childs[child - 1] : null;
                BNode<E> right = (child < node.count)    ? node.childs[child + 1] : null;

                // redistribucion desde hermano izquierdo
                if (left != null && left.count > minKeys) {
                    for (int i = c.count; i > 0; i--) {
                        c.keys[i] = c.keys[i - 1];
                    }
                    c.keys[0] = node.keys[child - 1];
                    c.count++;
                    node.keys[child - 1] = left.keys[left.count - 1];
                    left.count--;
                }
                // redistribucion desde hermano derecho
                else if (right != null && right.count > minKeys) {
                    c.keys[c.count] = node.keys[child];
                    c.count++;
                    node.keys[child] = right.keys[0];
                    for (int i = 0; i < right.count - 1; i++) {
                        right.keys[i] = right.keys[i + 1];
                    }
                    right.count--;
                }
                // fusion con hermano izquierdo
                else if (left != null) {
                    left.keys[left.count] = node.keys[child - 1];
                    int pos = left.count + 1;
                    for (int i = 0; i < c.count; i++) {
                        left.keys[pos + i] = c.keys[i];
                    }
                    left.count += 1 + c.count;
                    for (int i = child - 1; i < node.count - 1; i++) {
                        node.keys[i] = node.keys[i + 1];
                        node.childs[i + 1] = node.childs[i + 2];
                    }
                    node.count--;
                }
                // fusion con hermano derecho
                else if (right != null) {
                    c.keys[c.count] = node.keys[child];
                    c.count++;
                    for (int i = 0; i < right.count; i++) {
                        c.keys[c.count + i] = right.keys[i];
                    }
                    c.count += right.count;
                    for (int i = child; i < node.count - 1; i++) {
                        node.keys[i] = node.keys[i + 1];
                        node.childs[i + 1] = node.childs[i + 2];
                    }
                    node.count--;
                }
            }
        }
    }

    // 03. Metodo building_Btree: construye un BTree<Integer> desde archivo
    public static BTree<Integer> building_Btree(String filename) throws Exception {
        // abrimos el archivo para lectura
        java.io.BufferedReader reader =
            new java.io.BufferedReader(new java.io.FileReader(filename));
        // leemos el orden del arbol (primera linea)
        int order = Integer.parseInt(reader.readLine().trim());

        // mapas auxiliares para nodos por nivel e id
        java.util.Map<Integer, java.util.List<BNode<Integer>>> nodesByLevel =
            new java.util.HashMap<>();
        String line;
        int maxLevel = 0;

        // leemos cada linea que describe un nodo
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            // partes: nivel, idNodo, claves...
            String[] parts = line.split(",");
            int level = Integer.parseInt(parts[0].trim());
            int id    = Integer.parseInt(parts[1].trim());

            // creamos el nodo y asignamos sus claves
            BNode<Integer> node = new BNode<>(order);
            node.count = parts.length - 2;
            for (int i = 0; i < node.count; i++) {
                node.keys[i] = Integer.parseInt(parts[i + 2].trim());
            }

            // almacenamos en el mapa por nivel
            nodesByLevel.computeIfAbsent(level, k -> new java.util.ArrayList<>())
                        .add(node);
            if (level > maxLevel) maxLevel = level;
        }
        reader.close();

        // vinculamos los hijos de cada padre
        for (int lvl = 1; lvl <= maxLevel; lvl++) {
            java.util.List<BNode<Integer>> parents  = nodesByLevel.get(lvl - 1);
            java.util.List<BNode<Integer>> children = nodesByLevel.get(lvl);
            if (parents == null || children == null) {
                throw new Exception("Estructura de niveles incompleta");
            }
            java.util.Set<BNode<Integer>> assigned = new java.util.HashSet<>();
            for (BNode<Integer> parent : parents) {
                int k = parent.count;
                for (int j = 0; j <= k; j++) {
                    for (BNode<Integer> child : children) {
                        if (assigned.contains(child)) continue;
                        Integer firstKey = child.keys[0];
                        boolean gtLower = (j == 0) ||
                            (firstKey.compareTo(parent.keys[j - 1]) > 0);
                        boolean leUpper = (j == k) ||
                            (firstKey.compareTo(parent.keys[j]) < 0);
                        if (gtLower && leUpper) {
                            parent.childs[j] = child;
                            assigned.add(child);
                            break;
                        }
                    }
                }
            }
        }

        // obtenemos la raiz (nivel 0)
        java.util.List<BNode<Integer>> roots = nodesByLevel.get(0);
        if (roots == null || roots.isEmpty()) {
            throw new Exception("Raiz no encontrada");
        }
        BTree<Integer> tree = new BTree<>(order);
        tree.root = roots.get(0);

        // verificamos propiedades de B-Tree
        int minChildren = (order + 1) / 2;
        int minKeys     = minChildren - 1;
        for (java.util.List<BNode<Integer>> lvlNodes : nodesByLevel.values()) {
            for (BNode<Integer> node : lvlNodes) {
                // maximo de claves
                if (node.count > order - 1) {
                    throw new Exception("Nodo con mas claves del maximo");
                }
                // minimo de claves (salvo raiz)
                if (node != tree.root && node.count < minKeys) {
                    throw new Exception("Nodo con menos claves del minimo");
                }
            }
        }

        return tree;
    }
}

