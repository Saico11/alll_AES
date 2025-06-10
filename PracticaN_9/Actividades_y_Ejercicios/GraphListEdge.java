import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Implementacion de grafo usando lista de estructuras de aristas
public class GraphListEdge<V,E> {
    // Secuencia de vertices del grafo
    protected ArrayList<VertexObj<V,E>> secVertex;
    
    // Secuencia de aristas del grafo
    protected ArrayList<EdgeObj<V,E>> secEdge;

    // Constructor que inicializa las secuencias
    public GraphListEdge() {
        this.secVertex = new ArrayList<VertexObj<V,E>>();
        this.secEdge = new ArrayList<EdgeObj<V,E>>();
    }

    // a) Inserta un vertice si no existe
    public void insertVertex(V data) {
        // Verifica si el vertice ya existe
        if (!searchVertex(data)) {
            // Crea nuevo vertice con posicion igual al tamaño actual
            VertexObj<V,E> newVertex = new VertexObj<>(data, secVertex.size());
            // Agrega a la secuencia
            secVertex.add(newVertex);
        }
    }

    // b) Inserta una arista si no existe
    public void insertEdge(V verOri, V verDes) {
        insertEdge(verOri, verDes, null);
    }

    // Version sobrecargada para aristas con informacion
    public void insertEdge(V verOri, V verDes, E info) {
        // Busca los vertices
        VertexObj<V,E> vertexOri = getVertex(verOri);
        VertexObj<V,E> vertexDes = getVertex(verDes);
        
        // Verifica que ambos vertices existan
        if (vertexOri != null && vertexDes != null) {
            // Verifica si la arista ya existe
            if (!searchEdge(verOri, verDes)) {
                // Crea nueva arista con posicion igual al tamaño actual
                EdgeObj<V,E> newEdge = new EdgeObj<>(vertexOri, vertexDes, info, secEdge.size());
                // Agrega a la secuencia
                secEdge.add(newEdge);
            }
        }
    }

    // c) Busca un vertice por su informacion
    public boolean searchVertex(V data) {
        return getVertex(data) != null;
    }

    // d) Busca una arista por sus vertices extremos
    public boolean searchEdge(V verOri, V verDes) {
        VertexObj<V,E> vertexOri = getVertex(verOri);
        VertexObj<V,E> vertexDes = getVertex(verDes);
        
        if (vertexOri != null && vertexDes != null) {
            // Crea arista temporal para comparacion
            EdgeObj<V,E> tempEdge = new EdgeObj<>(vertexOri, vertexDes, null, -1);
            // Busca en la secuencia de aristas
            for (EdgeObj<V,E> edge : secEdge) {
                if (edge.equals(tempEdge)) {
                    return true;
                }
            }
        }
        return false;
    }

    // e) Recorrido BFS desde un vertice
    public void bfs(V startVertex) {
        VertexObj<V,E> start = getVertex(startVertex);
        if (start != null) {
            // Estructuras para BFS
            Queue<VertexObj<V,E>> queue = new LinkedList<>();
            ArrayList<VertexObj<V,E>> visited = new ArrayList<>();
            
            // Inicializacion
            queue.add(start);
            visited.add(start);
            
            while (!queue.isEmpty()) {
                VertexObj<V,E> current = queue.poll();
                System.out.print(current.info + " ");
                
                // Encontrar todos los vecinos
                for (EdgeObj<V,E> edge : secEdge) {
                    if (edge.endVertex1.equals(current) && !visited.contains(edge.endVertex2)) {
                        visited.add(edge.endVertex2);
                        queue.add(edge.endVertex2);
                    } else if (edge.endVertex2.equals(current) && !visited.contains(edge.endVertex1)) {
                        visited.add(edge.endVertex1);
                        queue.add(edge.endVertex1);
                    }
                }
            }
            System.out.println();
        }
    }

    // Metodo auxiliar para obtener vertice por su informacion
    private VertexObj<V,E> getVertex(V data) {
        for (VertexObj<V,E> vertex : secVertex) {
            if (vertex.info.equals(data)) {
                return vertex;
            }
        }
        return null;
    }

    // Representacion en String del grafo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(secVertex).append("\n");
        sb.append("Aristas: ").append(secEdge);
        return sb.toString();
    }
}
