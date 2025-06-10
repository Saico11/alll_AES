import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;


// Extiende GraphLink para manejar grafos dirigidos
public class GraphLinkDirigido<E> extends GraphLink<E> {
    
    public Vertex<E> getVertex(E data) {
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().equals(data)) {
                return current;
            }
            current = listVertex.getNext();
        }
        return null;
    }

    public List<Vertex<E>> getVerticesAsList() {
        List<Vertex<E>> result = new ArrayList<>();
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            result.add(current);
            current = listVertex.getNext();
        }
        return result;
    }


    // Sobreescribe insertEdge para hacerlo dirigido
    @Override
    public void insertEdge(E verOri, E verDes) {
        Vertex<E> vertexOri = getVertex(verOri);
        Vertex<E> vertexDes = getVertex(verDes);
        
        if (vertexOri != null && vertexDes != null) {
            if (!searchEdgeDirigido(verOri, verDes)) {
                vertexOri.listAdj.insert(new Edge<E>(vertexDes, -1));
            }
        }
    }
    
    // Versión para aristas ponderadas dirigidas
    public void insertEdgeWeightDirigido(E verOri, E verDes, int weight) {
        Vertex<E> vertexOri = getVertex(verOri);
        Vertex<E> vertexDes = getVertex(verDes);
        
        if (vertexOri != null && vertexDes != null) {
            if (!searchEdgeDirigido(verOri, verDes)) {
                vertexOri.listAdj.insert(new Edge<E>(vertexDes, weight));
            }
        }
    }

    // Búsqueda de arista dirigida
    public boolean searchEdgeDirigido(E verOri, E verDes) {
        Vertex<E> vertexOri = getVertex(verOri);
        Vertex<E> vertexDes = getVertex(verDes);
        
        if (vertexOri != null && vertexDes != null) {
            Edge<E> edge = vertexOri.listAdj.getFirst();
            while (edge != null) {
                if (edge.refDest.equals(vertexDes)) {
                    return true;
                }
                edge = vertexOri.listAdj.getNext();
            }
        }
        return false;
    }

    // Grado de salida de un nodo (aristas que salen)
    public int gradoSalida(E data) {
        Vertex<E> vertex = getVertex(data);
        return (vertex != null) ? vertex.listAdj.size() : -1;
    }

    // Grado de entrada de un nodo (aristas que entran)
    public int gradoEntrada(E data) {
        int count = 0;
        Vertex<E> target = getVertex(data);
        if (target == null) return -1;
        
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            Edge<E> edge = current.listAdj.getFirst();
            while (edge != null) {
                if (edge.refDest.equals(target)) {
                    count++;
                }
                edge = current.listAdj.getNext();
            }
            current = listVertex.getNext();
        }
        return count;
    }

       // Verifica si el grafo es un camino dirigido
    public boolean esCaminoDirigido() {
        int verticesConGradoSalida1 = 0;
        int verticesConGradoEntrada1 = 0;
        int verticesConGradoSalida0 = 0;
        int totalVertices = 0;
        
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            int salida = gradoSalida(current.getData());
            int entrada = gradoEntrada(current.getData());
            
            if (salida == 1 && entrada == 0) {
                verticesConGradoSalida1++;
            } else if (salida == 0 && entrada == 1) {
                verticesConGradoEntrada1++;
            } else if (salida == 0 && entrada == 0) {
                verticesConGradoSalida0++;
            } else if (salida != 1 || entrada != 1) {
                return false;
            }
            
            totalVertices++;
            current = listVertex.getNext();
        }
        
        return (verticesConGradoSalida1 == 1 && verticesConGradoEntrada1 == 1) ||
               (verticesConGradoSalida0 == totalVertices); // Grafo vacío
    }

    
    // Verifica si el grafo es un ciclo dirigido
    public boolean esCicloDirigido() {
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            if (gradoSalida(current.getData()) != 1 || gradoEntrada(current.getData()) != 1) {
                return false;
            }
            current = listVertex.getNext();
        }
        return listVertex.size() > 0; // Debe tener al menos un vértice
    }

    // Verifica si el grafo es una rueda dirigida
    public boolean esRuedaDirigida() {
        int centro = -1;
        int perifericos = 0;
        int totalVertices = listVertex.size();
        
        if (totalVertices < 3) return false;
        
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            int salida = gradoSalida(current.getData());
            int entrada = gradoEntrada(current.getData());
            
            if (salida == totalVertices - 1 && entrada == 0) {
                centro++;
            } else if (salida == 1 && entrada == 2) {
                perifericos++;
            } else {
                return false;
            }
            current = listVertex.getNext();
        }
        
        return centro == 1 && perifericos == totalVertices - 1;
    }

     // En GraphLinkDirigido
public String representacionFormalDirigida() {
    StringBuilder sb = new StringBuilder();
    
    // Lista de vértices
    sb.append("V = {");
    Vertex<E> current = listVertex.getFirst();
    while (current != null) {
        sb.append(current.getData());
        current = listVertex.getNext();
        if (current != null) sb.append(", ");
    }
    sb.append("}\n");
    
    // Lista de aristas dirigidas
    sb.append("A = {");
    boolean first = true;
    
    Vertex<E> v = listVertex.getFirst();
    while (v != null) {
        Edge<E> edge = v.listAdj.getFirst();
        while (edge != null) {
            if (!first) sb.append(", ");
            sb.append("<").append(v.getData()).append(", ").append(edge.refDest.getData()).append(">");
            first = false;
            edge = v.listAdj.getNext();
        }
        v = listVertex.getNext();
    }
    sb.append("}");
    
    return sb.toString();
} 

// En GraphLinkDirigido
public String representacionMatrizAdyacenciaDirigida() {
    List<Vertex<E>> vertices = getVerticesAsList();
    int size = vertices.size();
    int[][] matriz = new int[size][size];
    
    // Mapeo de vértices a índices
    Map<E, Integer> indexMap = new HashMap<>();
    for (int i = 0; i < size; i++) {
        indexMap.put(vertices.get(i).getData(), i);
    }
    
    // Llenar matriz
    for (int i = 0; i < size; i++) {
        Vertex<E> v = vertices.get(i);
        Edge<E> edge = v.listAdj.getFirst();
        while (edge != null) {
            int j = indexMap.get(edge.refDest.getData());
            matriz[i][j] = 1; // Solo llenamos la dirección i→j
            edge = v.listAdj.getNext();
        }
    }
    
    // Construir salida
    StringBuilder sb = new StringBuilder();
    sb.append("   ");
    for (Vertex<E> v : vertices) {
        sb.append(String.format("%3s", v.getData()));
    }
    sb.append("\n");
    
    for (int i = 0; i < size; i++) {
        sb.append(String.format("%3s", vertices.get(i).getData()));
        for (int j = 0; j < size; j++) {
            sb.append(String.format("%3d", matriz[i][j]));
        }
        sb.append("\n");
    }
    
    return sb.toString();
} 

public int gradoSalida(E data) {
        Vertex<E> vertex = getVertex(data);
        return (vertex != null) ? vertex.listAdj.size() : -1;
    }

    public int gradoEntrada(E data) {
        int count = 0;
        Vertex<E> target = getVertex(data);
        if (target == null) return -1;
        
        Vertex<E> current = (Vertex<E>) listVertex.getFirst();
        while (current != null) {
            Edge<E> edge = (Edge<E>) current.listAdj.getFirst();
            while (edge != null) {
                if (edge.refDest.equals(target)) {
                    count++;
                }
                edge = (Edge<E>) current.listAdj.getNext();
            }
            current = (Vertex<E>) listVertex.getNext();
        }
        return count;
    }

}
