
// Clase para probar la implementacion del BST
public class Prueba {
    public static void main(String[] args) {
        try {
            // Crear un BST de enteros
            LinkedBST<Integer> bst = new LinkedBST<>();
            
            // Insertar elementos
            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            bst.insert(20);
            bst.insert(40);
            bst.insert(60);
            bst.insert(80);
            
            // Probar recorridos
            System.out.println("InOrden: " + bst.inOrder());
            System.out.println("PreOrden: " + bst.preOrder());
            System.out.println("PostOrden: " + bst.postOrder());
            
            // Probar busqueda
            System.out.println("Buscar 40: " + bst.search(40));
            
            // Probar eliminacion
            bst.delete(30);
            System.out.println("InOrden despues de eliminar 30: " + bst.inOrder());
            
            // Probar metodos de ejercicios
            System.out.println("Numero de nodos totales: " + bst.countAllNodes());
            System.out.println("Numero de nodos no-hoja: " + bst.countNodes());
            System.out.println("Altura del subarbol con raiz 70: " + bst.height(70));
            System.out.println("Amplitud del arbol: " + bst.amplitude(2));
            System.out.println("Area del BST: " + bst.areaBST());
            
            // Dibujar el BST
            System.out.println("\nRepresentacion grafica del BST:");
            System.out.println(bst.drawBST());
            
            // Representacion parentetica
            System.out.println("\nRepresentacion parentetica:");
            System.out.println(bst.parenthesize());
            
            // Probar metodo sameArea
            LinkedBST<Integer> bst2 = new LinkedBST<>();
            bst2.insert(50);
            bst2.insert(30);
            bst2.insert(70);
            System.out.println("\nMisma area? " + sameArea(bst, bst2));
            
        } catch (ItemDuplicated | ItemNotFound | ExceptionIsEmpty e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    // 2c. Metodo para comparar areas de dos arboles
    public static <E extends Comparable<E>> boolean sameArea(LinkedBST<E> tree1, LinkedBST<E> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }
}
