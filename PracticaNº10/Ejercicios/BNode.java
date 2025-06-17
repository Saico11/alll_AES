// clase que representa un nodo de un B-Tree
public class BNode<E extends Comparable<E>> {
    // contador estatico para asignar id unicos
    private static int counter = 0;
    // arreglo de claves de tamano maximo order-1
    E[] keys;
    // arreglo de hijos de tamano maximo order
    BNode<E>[] childs;
    // numero actual de claves en el nodo
    int count;
    // identificador unico del nodo
    int id;
    
    // constructor: crea un nodo vacio de orden dado
    @SuppressWarnings("unchecked")
    public BNode(int order) {
        this.id = counter++;                           // asigna id y avanza contador
        this.keys = (E[]) new Comparable[order - 1];   // inicializa arreglo de claves
        this.childs = new BNode[order];                // inicializa arreglo de hijos
        this.count = 0;                                // inicia sin claves
    }
    
    // verifica si el nodo esta lleno (count == order-1)
    public boolean nodeFull(int order) {
        return this.count == order - 1;
    }
    
    // verifica si el nodo esta vacio
    public boolean nodeEmpty() {
        return this.count == 0;
    }
    
    // busca una clave en el nodo:
    // si la encuentra, devuelve indice >=0;
    // si no, devuelve (-pos-1), donde pos es el indice de hijo donde seguir
    public int searchNode(E key) {
        int i = 0;
        // avanza mientras claves < key
        while (i < this.count && key.compareTo(this.keys[i]) > 0) {
            i++;
        }
        // si coincide con una clave, devuelve su indice
        if (i < this.count && key.compareTo(this.keys[i]) == 0) {
            return i;
        }
        // si no, devuelve codificacion negativa para seleccion de hijo
        return -i - 1;
    }
    
    // representacion del nodo: muestra id y claves
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nodo ").append(this.id).append(": ");
        for (int i = 0; i < this.count; i++) {
            sb.append(this.keys[i]);
            if (i < this.count - 1) sb.append(", ");
        }
        return sb.toString();
    }
}

