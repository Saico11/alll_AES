import java.util.ArrayList;
import java.util.HashMap;

// Clase para probar la implementación del grafo
public class TestGraph {
    // Método principal
    public static void main(String[] args) {
        // Crea un grafo no dirigido de Strings
        GraphLink<String> graph = new GraphLink<>();

        // Inserta vértices al grafo
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");

        // Inserta aristas entre los vértices
        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "E");
        graph.insertEdge("D", "E");

        // Imprime el grafo inicial
        System.out.println("Grafo inicial:");
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

        System.out.println("\nCaso 1: Camino existente (A -> E)");
        ArrayList<String> path1 = graph.bfsPath("A", "E");
        System.out.println("Camino encontrado: " + path1); 

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
}


