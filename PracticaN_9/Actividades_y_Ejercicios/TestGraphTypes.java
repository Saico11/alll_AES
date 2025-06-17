public class TestGraphTypes {
    public static void main(String[] args) {
        // ========== PRUEBAS DE TIPOS DE GRAFOS ========== //
        
        // a) Grafo Camino P4
        GraphLink<String> caminoP4 = new GraphLink<>();
        caminoP4.insertVertex("A");
        caminoP4.insertVertex("B");
        caminoP4.insertVertex("C");
        caminoP4.insertVertex("D");
        caminoP4.insertEdge("A", "B");
        caminoP4.insertEdge("B", "C");
        caminoP4.insertEdge("C", "D");
        
        System.out.println("=== Grafo Camino P4 ===");
        System.out.println("Es camino: " + caminoP4.esCamino());
        System.out.println("Es ciclo: " + caminoP4.esCiclo());
        System.out.println("Es rueda: " + caminoP4.esRueda());
        System.out.println("Es completo: " + caminoP4.esCompleto());
        System.out.println("Grado de B: " + caminoP4.gradoNodo("B"));
        
        // b) Grafo Ciclo C5
        GraphLink<String> cicloC5 = new GraphLink<>();
        cicloC5.insertVertex("A");
        cicloC5.insertVertex("B");
        cicloC5.insertVertex("C");
        cicloC5.insertVertex("D");
        cicloC5.insertVertex("E");
        cicloC5.insertEdge("A", "B");
        cicloC5.insertEdge("B", "C");
        cicloC5.insertEdge("C", "D");
        cicloC5.insertEdge("D", "E");
        cicloC5.insertEdge("E", "A");
        
        System.out.println("\n=== Grafo Ciclo C5 ===");
        System.out.println("Es camino: " + cicloC5.esCamino());
        System.out.println("Es ciclo: " + cicloC5.esCiclo());
        System.out.println("Es rueda: " + cicloC5.esRueda());
        System.out.println("Es completo: " + cicloC5.esCompleto());
        System.out.println("Grado de A: " + cicloC5.gradoNodo("A"));
        
        // c) Grafo Rueda W4
        GraphLink<String> ruedaW4 = new GraphLink<>();
        ruedaW4.insertVertex("Center");
        ruedaW4.insertVertex("A");
        ruedaW4.insertVertex("B");
        ruedaW4.insertVertex("C");
        ruedaW4.insertEdge("Center", "A");
        ruedaW4.insertEdge("Center", "B");
        ruedaW4.insertEdge("Center", "C");
        ruedaW4.insertEdge("A", "B");
        ruedaW4.insertEdge("B", "C");
        ruedaW4.insertEdge("C", "A");
        
        System.out.println("\n=== Grafo Rueda W4 ===");
        System.out.println("Es camino: " + ruedaW4.esCamino());
        System.out.println("Es ciclo: " + ruedaW4.esCiclo());
        System.out.println("Es rueda: " + ruedaW4.esRueda());
        System.out.println("Es completo: " + ruedaW4.esCompleto());
        System.out.println("Grado de Center: " + ruedaW4.gradoNodo("Center"));
        
        // d) Grafo Completo K4
        GraphLink<String> completoK4 = new GraphLink<>();
        completoK4.insertVertex("A");
        completoK4.insertVertex("B");
        completoK4.insertVertex("C");
        completoK4.insertVertex("D");
        completoK4.insertEdge("A", "B");
        completoK4.insertEdge("A", "C");
        completoK4.insertEdge("A", "D");
        completoK4.insertEdge("B", "C");
        completoK4.insertEdge("B", "D");
        completoK4.insertEdge("C", "D");
        
        System.out.println("\n=== Grafo Completo K4 ===");
        System.out.println("Es camino: " + completoK4.esCamino());
        System.out.println("Es ciclo: " + completoK4.esCiclo());
        System.out.println("Es rueda: " + completoK4.esRueda());
        System.out.println("Es completo: " + completoK4.esCompleto());
        System.out.println("Grado de A: " + completoK4.gradoNodo("A"));
    }
}
