public class TestGraphRepresentations {
    public static void main(String[] args) {
        // Crear grafo no dirigido de ejemplo
        GraphLink<String> graph = new GraphLink<>();
        
        // Insertar vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        
        // Insertar aristas
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "C");
        graph.insertEdge("C", "D");
        
        // ========== PRUEBAS DE REPRESENTACIONES ========== //
        
        System.out.println("=== REPRESENTACIÓN FORMAL ===");
        System.out.println(graph.representacionFormal());
        
        System.out.println("\n=== LISTAS DE ADYACENCIA ===");
        System.out.println(graph.representacionListasAdyacencia());
        
        System.out.println("\n=== MATRIZ DE ADYACENCIA ===");
        System.out.println(graph.representacionMatrizAdyacencia());
        
        // ========== PRUEBAS CON GRAFO COMPLETO K4 ========== //
        GraphLink<String> k4 = new GraphLink<>();
        k4.insertVertex("A");
        k4.insertVertex("B");
        k4.insertVertex("C");
        k4.insertVertex("D");
        k4.insertEdge("A", "B");
        k4.insertEdge("A", "C");
        k4.insertEdge("A", "D");
        k4.insertEdge("B", "C");
        k4.insertEdge("B", "D");
        k4.insertEdge("C", "D");
        
        System.out.println("\n=== GRAFO COMPLETO K4 ===");
        System.out.println("Representación formal:");
        System.out.println(k4.representacionFormal());
        
        System.out.println("\nMatriz de adyacencia:");
        System.out.println(k4.representacionMatrizAdyacencia());
    }
}
