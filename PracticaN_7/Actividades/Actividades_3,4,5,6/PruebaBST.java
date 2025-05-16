
public class PruebaBST {
    public static void main(String[] args) {
        try {
            // 1. Crear un BST de enteros
            LinkedBST<Integer> bst = new LinkedBST<>();
            System.out.println("Árbol creado. ¿Está vacío? " + bst.isEmpty());
            
            // 2. Pruebas de inserción
            System.out.println("\n=== INSERTANDO ELEMENTOS ===");
            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            bst.insert(20);
            bst.insert(40);
            bst.insert(60);
            bst.insert(80);
            
            // Intentar insertar duplicado
            try {
                bst.insert(50);
            } catch (ItemDuplicated e) {
                System.out.println("Excepción capturada correctamente al insertar duplicado: " + e.getMessage());
            }
            
            System.out.println("Árbol después de inserciones:");
            System.out.println(bst.toString());
            
            // 3. Pruebas de búsqueda
            System.out.println("\n=== BUSCANDO ELEMENTOS ===");
            System.out.println("Buscar 40: " + bst.search(40));
            System.out.println("Buscar 70: " + bst.search(70));
            
            // Buscar elemento no existente
            try {
                System.out.println("Buscar 100: " + bst.search(100));
            } catch (ItemNotFound e) {
                System.out.println("Excepción capturada correctamente al buscar elemento no existente: " + e.getMessage());
            }
            
            // 4. Pruebas de recorridos
            System.out.println("\n=== RECORRIDOS DEL ÁRBOL ===");
            System.out.println("Recorrido InOrder: " + bst.inOrder());
            System.out.println("Recorrido PreOrder: " + bst.preOrder());
            System.out.println("Recorrido PostOrder: " + bst.postOrder());
            
            // 5. Pruebas de mínimo y máximo
            System.out.println("\n=== MÍNIMO Y MÁXIMO ===");
            System.out.println("Elemento mínimo: " + bst.findMin());
            System.out.println("Elemento máximo: " + bst.findMax());
            
            // 6. Pruebas de eliminación
            System.out.println("\n=== ELIMINANDO ELEMENTOS ===");
            
            // Caso 1: Eliminar hoja (20)
            System.out.println("\nEliminando hoja (20):");
            bst.delete(20);
            System.out.println("InOrder después de eliminar 20: " + bst.inOrder());
            
            // Caso 2: Eliminar nodo con un hijo (30 solo tiene hijo derecho 40 ahora)
            System.out.println("\nEliminando nodo con un hijo (30):");
            bst.delete(30);
            System.out.println("InOrder después de eliminar 30: " + bst.inOrder());
            
            // Caso 3: Eliminar nodo con dos hijos (70)
            System.out.println("\nEliminando nodo con dos hijos (70):");
            bst.delete(70);
            System.out.println("InOrder después de eliminar 70: " + bst.inOrder());
            
            // Intentar eliminar de árbol vacío
            LinkedBST<Integer> arbolVacio = new LinkedBST<>();
            try {
                arbolVacio.delete(10);
            } catch (ExceptionIsEmpty e) {
                System.out.println("\nExcepción capturada correctamente al eliminar de árbol vacío: " + e.getMessage());
            }
            
            // 7. Pruebas con strings
            System.out.println("\n=== PRUEBA CON STRINGS ===");
            LinkedBST<String> bstStrings = new LinkedBST<>();
            bstStrings.insert("Manzana");
            bstStrings.insert("Banana");
            bstStrings.insert("Cereza");
            bstStrings.insert("Durazno");
            
            System.out.println("Árbol de strings:");
            System.out.println("InOrder: " + bstStrings.inOrder());
            System.out.println("Buscar 'Cereza': " + bstStrings.search("Cereza"));
            
            // 8. Prueba de toString
            System.out.println("\n=== PRUEBA DE TOSTRING ===");
            System.out.println(bst.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
