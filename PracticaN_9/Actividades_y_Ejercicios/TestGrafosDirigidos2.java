public class TestGrafosDirigidos2 {
    public static void main(String[] args) {
        // Crear grafo dirigido cíclico
        GraphLinkDirigido<String> ciclo = new GraphLinkDirigido<>();
        ciclo.insertVertex("A");
        ciclo.insertVertex("B");
        ciclo.insertVertex("C");
        ciclo.insertEdge("A", "B");
        ciclo.insertEdge("B", "C");
        ciclo.insertEdge("C", "A");
        
        // Crear grafo isomorfo al anterior
        GraphLinkDirigido<String> isomorfo = new GraphLinkDirigido<>();
        isomorfo.insertVertex("X");
        isomorfo.insertVertex("Y");
        isomorfo.insertVertex("Z");
        isomorfo.insertEdge("X", "Y");
        isomorfo.insertEdge("Y", "Z");
        isomorfo.insertEdge("Z", "X");
        
        // Crear grafo no conexo
        GraphLinkDirigido<String> noConexo = new GraphLinkDirigido<>();
        noConexo.insertVertex("P");
        noConexo.insertVertex("Q");
        noConexo.insertVertex("R");
        noConexo.insertEdge("P", "Q");
        
        // Pruebas
        System.out.println("=== PRUEBAS GRAFO DIRIGIDO ===");
        System.out.println("¿Ciclo es isomorfo a Isomorfo? " + ciclo.esIsomorfo(isomorfo));
        System.out.println("¿Ciclo es plano? " + ciclo.esPlano());
        System.out.println("¿Ciclo es conexo? " + ciclo.esConexo());
        System.out.println("¿Ciclo es auto-complementario? " + ciclo.esAutoComplementario());
        
        System.out.println("\n¿No conexo es conexo? " + noConexo.esConexo());
        
        // Grafo auto-complementario de 4 vértices
        GraphLinkDirigido<String> autoComplementario = new GraphLinkDirigido<>();
        autoComplementario.insertVertex("V1");
        autoComplementario.insertVertex("V2");
        autoComplementario.insertVertex("V3");
        autoComplementario.insertVertex("V4");
        autoComplementario.insertEdge("V1", "V2");
        autoComplementario.insertEdge("V2", "V3");
        autoComplementario.insertEdge("V3", "V4");
        autoComplementario.insertEdge("V4", "V1");
        autoComplementario.insertEdge("V1", "V3");
        autoComplementario.insertEdge("V2", "V4");
        
        System.out.println("\n¿Grafo especial es auto-complementario? " + 
                          autoComplementario.esAutoComplementario());
    }
}
