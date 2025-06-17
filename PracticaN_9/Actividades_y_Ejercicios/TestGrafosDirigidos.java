public class TestGrafosDirigidos {
    public static void main(String[] args) {
        System.out.println("=== PRUEBAS GRAPH LINK DIRIGIDO ===");
        testGraphLinkDirigido();
        
        System.out.println("\n=== PRUEBAS GRAPH LIST EDGE DIRIGIDO ===");
        testGraphListEdgeDirigido();
    }

    public static void testGraphLinkDirigido() {
        GraphLinkDirigido<String> grafo = new GraphLinkDirigido<>();
        
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");
        
        grafo.insertEdge("A", "B");
        grafo.insertEdge("B", "C");
        grafo.insertEdge("C", "D");
        grafo.insertEdge("D", "A");
        
        System.out.println("\nRepresentación formal:");
        System.out.println(grafo.representacionFormalDirigida());
        
        System.out.println("\nMatriz de adyacencia:");
        System.out.println(grafo.representacionMatrizAdyacenciaDirigida());
        
        System.out.println("\nGrado de entrada de A: " + grafo.gradoEntrada("A"));
        System.out.println("Grado de salida de A: " + grafo.gradoSalida("A"));
    }

    public static void testGraphListEdgeDirigido() {
        GraphListEdgeDirigido<String, Integer> grafo = new GraphListEdgeDirigido<>();
        
        grafo.insertVertex("X");
        grafo.insertVertex("Y");
        grafo.insertVertex("Z");
        
        grafo.insertEdge("X", "Y", 1);
        grafo.insertEdge("Y", "Z", 2);
        grafo.insertEdge("Z", "X", 3);
        
        System.out.println("\nGrado de entrada de X: " + grafo.gradoEntrada("X"));
        System.out.println("Grado de salida de X: " + grafo.gradoSalida("X"));
        
        System.out.println("\n¿Existe arista X→Y? " + grafo.searchEdgeDirigido("X", "Y"));
        System.out.println("¿Existe arista Y→X? " + grafo.searchEdgeDirigido("Y", "X"));
    }
}
