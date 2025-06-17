import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class TestGrafosCompleto {
    public static void main(String[] args) {
        // ========== PRUEBAS GraphLink ========== //
        System.out.println("========== PRUEBAS GraphLink ==========");
        testGraphLink();
        
        // ========== PRUEBAS GraphListEdge ========== //
        System.out.println("\n\n========== PRUEBAS GraphListEdge ==========");
        testGraphListEdge();
    }

    public static void testGraphLink() {
        // Crea un grafo no dirigido de Strings
        GraphLink<String> graph = new GraphLink<>();

        // Inserta vértices al grafo
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F"); // Vértice desconectado

        // ================= PRUEBAS BÁSICAS ================= //
        System.out.println("=== PRUEBAS BÁSICAS ===");
        
        // Inserta aristas no ponderadas
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "E");
        graph.insertEdge("D", "E");

        // Imprime el grafo inicial
        System.out.println("\nGrafo inicial:");
        System.out.println(graph);

        // Prueba búsqueda de vértices
        System.out.println("\n¿Existe el vértice 'A'? " + graph.searchVertex("A"));
        System.out.println("¿Existe el vértice 'F'? " + graph.searchVertex("F"));

        // Prueba búsqueda de aristas
        System.out.println("\n¿Existe arista entre A y B? " + graph.searchEdge("A", "B"));
        System.out.println("¿Existe arista entre A y D? " + graph.searchEdge("A", "D"));

        // Prueba recorrido DFS desde A
        System.out.println("\nDFS desde A:");
        graph.dfs("A");

        // Prueba recorrido BFS desde A
        System.out.println("\nBFS desde A:");
        graph.bfs("A");

        // Prueba bfsPath
        System.out.println("\nCamino de A a E (BFS):");
        ArrayList<String> path1 = graph.bfsPath("A", "E");
        System.out.println("Camino encontrado: " + path1);

        // ================= PRUEBAS GRAFO PONDERADO ================= //
        System.out.println("\n=== PRUEBAS GRAFO PONDERADO ===");
        
        // Crea un nuevo grafo ponderado
        GraphLink<String> weightedGraph = new GraphLink<>();
        weightedGraph.insertVertex("A");
        weightedGraph.insertVertex("B");
        weightedGraph.insertVertex("C");
        weightedGraph.insertVertex("D");

        // Inserta aristas con pesos
        weightedGraph.insertEdgeWeight("A", "B", 4);
        weightedGraph.insertEdgeWeight("A", "C", 2);
        weightedGraph.insertEdgeWeight("B", "D", 5);
        weightedGraph.insertEdgeWeight("C", "D", 1);
        weightedGraph.insertEdgeWeight("B", "C", 1);

        System.out.println("\nGrafo ponderado inicial:");
        System.out.println(weightedGraph);

        // Prueba insertEdgeWeight
        System.out.println("\nActualizando peso de A-B a 3:");
        weightedGraph.insertEdgeWeight("A", "B", 3);
        System.out.println(weightedGraph);

        // Prueba shortPath (BFS para no ponderados)
        System.out.println("\nCamino más corto (BFS) de A a D:");
        ArrayList<String> shortPath = weightedGraph.shortPath("A", "D");
        System.out.println("Camino: " + shortPath);

        // Prueba isConexo
        System.out.println("\n¿El grafo es conexo? " + weightedGraph.isConexo());
        
        // Prueba con grafo no conexo
        weightedGraph.insertVertex("X");
        weightedGraph.insertVertex("Y");
        weightedGraph.insertEdge("X", "Y");
        System.out.println("Después de añadir vértices X-Y desconectados:");
        System.out.println("¿El grafo es conexo? " + weightedGraph.isConexo());

        // Prueba Dijkstra
        System.out.println("\nCamino más corto (Dijkstra) de A a D:");
        Stack<String> dijkstraPath = weightedGraph.Dijkstra("A", "D");
        System.out.print("Camino: ");
        while (!dijkstraPath.isEmpty()) {
            System.out.print(dijkstraPath.pop() + " ");
        }

        // ================= PRUEBAS ELIMINACIÓN ================= //
        System.out.println("\n\n=== PRUEBAS ELIMINACIÓN ===");
        
        // Prueba eliminación de arista entre B y D
        System.out.println("\nEliminando arista entre B y D...");
        graph.removeEdge("B", "D");
        System.out.println("¿Existe arista entre B y D ahora? " + graph.searchEdge("B", "D"));

        // Prueba eliminación de vértice C
        System.out.println("\nEliminando vértice 'C'...");
        graph.removeVertex("C");
        System.out.println("¿Existe el vértice 'C' ahora? " + graph.searchVertex("C"));
        System.out.println("Grafo después de eliminar 'C':");
        System.out.println(graph);
    }

    public static void testGraphListEdge() {
        // Crear grafo no dirigido con lista de aristas
        GraphListEdge<String, Integer> graph = new GraphListEdge<>();
        
        System.out.println("=== PRUEBAS GraphListEdge ===");
        
        // Insertar vertices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        
        // Insertar aristas con pesos
        graph.insertEdge("A", "B", 4);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "D", 5);
        graph.insertEdge("C", "D", 1);
        
        System.out.println("\nGrafo completo:");
        System.out.println(graph);
        
        // Prueba searchVertex
        System.out.println("\n¿Existe el vertice 'A'? " + graph.searchVertex("A"));
        System.out.println("¿Existe el vertice 'X'? " + graph.searchVertex("X"));
        
        // Prueba searchEdge
        System.out.println("\n¿Existe arista A-B? " + graph.searchEdge("A", "B"));
        System.out.println("¿Existe arista A-D? " + graph.searchEdge("A", "D"));
        
        // Prueba BFS
        System.out.println("\nBFS desde A:");
        graph.bfs("A");
        
        // Insertar vertice existente
        System.out.println("\nInsertando vertice existente 'A':");
        graph.insertVertex("A");
        System.out.println(graph);
        
        // Insertar arista existente
        System.out.println("\nInsertando arista existente A-B:");
        graph.insertEdge("A", "B", 10); // No deberia cambiar el peso
        System.out.println(graph);

        // Prueba con grafo no conexo
        System.out.println("\nInsertando vertices desconectados X-Y:");
        graph.insertVertex("X");
        graph.insertVertex("Y");
        graph.insertEdge("X", "Y", 3);
        System.out.println(graph);
    }
}
