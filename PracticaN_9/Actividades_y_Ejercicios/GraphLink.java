import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.List;
import java.util.Map;

// Implementación de grafo usando listas de adyacencia
public class GraphLink<E> {
    // Lista de vértices del grafo
    protected ListLinked<Vertex<E>> listVertex;
    private ListLinked<Edge<E>> secEdge; 
    // Constructor del grafo
    public GraphLink() {
        // Inicializa la lista de vértices
        listVertex = new ListLinked<Vertex<E>>();
        secEdge = new ListLinked<Edge<E>>();
    }
   
  protected Vertex<E> getVertex(E data) {
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().equals(data)) {
                return current;
            }
            current = listVertex.getNext();
        }
        return null;
    }
    protected List<Vertex<E>> getVerticesAsList() {
        List<Vertex<E>> vertices = new ArrayList<>();
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            vertices.add(current);
            current = listVertex.getNext();
        }
        return vertices;
    } 

    // Método para insertar un vértice al grafo
    public void insertVertex(E data) {
        // Crea un nuevo vértice con el dato recibido
        Vertex<E> newVertex = new Vertex<E>(data);
        // Verifica si el vértice ya existe
        if (!searchVertex(data)) {
            // Si no existe, lo agrega a la lista
            listVertex.insert(newVertex);
        }
    }

    // Método para insertar una arista sin peso
    public void insertEdge(E verOri, E verDes) {
        // Llama al método completo con peso -1
        insertEdge(verOri, verDes, -1);
    }

    // Método para insertar una arista con peso
    public void insertEdge(E verOri, E verDes, int weight) {
        // Obtiene los vértices origen y destino
        Vertex<E> vertexOri = getVertex(verOri);
        Vertex<E> vertexDes = getVertex(verDes);
        
        // Verifica que ambos vértices existan
        if (vertexOri != null && vertexDes != null) {
            // Verifica si la arista ya existe
            if (!searchEdge(verOri, verDes)) {
                // Inserta arista en ambos sentidos (grafo no dirigido)
                vertexOri.listAdj.insert(new Edge<E>(vertexDes, weight));
                vertexDes.listAdj.insert(new Edge<E>(vertexOri, weight));
            }
        }
    }

    // Método para buscar un vértice por su dato
    public boolean searchVertex(E data) {
        // Obtiene el primer vértice de la lista
        Vertex<E> aux = listVertex.getFirst();
        // Recorre la lista de vértices
        while (aux != null) {
            // Si encuentra el vértice, retorna true
            if (aux.getData().equals(data)) {
                return true;
            }
            // Avanza al siguiente vértice
            aux = listVertex.getNext();
        }
        // Si no lo encontró, retorna false
        return false;
    }

    // Método para buscar una arista entre dos vértices
    public boolean searchEdge(E verOri, E verDes) {
        // Obtiene los vértices origen y destino
        Vertex<E> vertexOri = getVertex(verOri);
        Vertex<E> vertexDes = getVertex(verDes);
        
        // Verifica que ambos vértices existan
        if (vertexOri != null && vertexDes != null) {
            // Obtiene la primera arista del vértice origen
            Edge<E> edge = vertexOri.listAdj.getFirst();
            // Recorre las aristas del vértice origen
            while (edge != null) {
                // Si encuentra la arista al vértice destino, retorna true
                if (edge.refDest.equals(vertexDes)) {
                    return true;
                }
                // Avanza a la siguiente arista
                edge = vertexOri.listAdj.getNext();
            }
        }
        // Si no encontró la arista, retorna false
        return false;
    }

    // Método auxiliar para obtener un vértice por su dato
    private Vertex<E> getVertex(E data) {
        // Obtiene el primer vértice de la lista
        Vertex<E> aux = listVertex.getFirst();
        // Recorre la lista de vértices
        while (aux != null) {
            // Si encuentra el vértice, lo retorna
            if (aux.getData().equals(data)) {
                return aux;
            }
            // Avanza al siguiente vértice
            aux = listVertex.getNext();
        }
        // Si no lo encontró, retorna null
        return null;
    }

    // Método para eliminar un vértice del grafo
    public void removeVertex(E data) {
        // Obtiene el vértice a eliminar
        Vertex<E> vertexToRemove = getVertex(data);
        // Verifica que el vértice exista
        if (vertexToRemove != null) {
            // Recorre todos los vértices del grafo
            Vertex<E> currentVertex = listVertex.getFirst();
            while (currentVertex != null) {
                // Si no es el vértice a eliminar
                if (!currentVertex.equals(vertexToRemove)) {
                    // Obtiene la primera arista del vértice actual
                    Edge<E> edge = currentVertex.listAdj.getFirst();
                    // Recorre las aristas del vértice actual
                    while (edge != null) {
                        // Si la arista apunta al vértice a eliminar
                        if (edge.refDest.equals(vertexToRemove)) {
                            // Elimina la arista
                            currentVertex.listAdj.remove(edge);
                        }
                        // Avanza a la siguiente arista
                        edge = currentVertex.listAdj.getNext();
                    }
                }
                // Avanza al siguiente vértice
                currentVertex = listVertex.getNext();
            }
            // Elimina el vértice de la lista
            listVertex.remove(vertexToRemove);
        }
    }

    // Método para eliminar una arista entre dos vértices
    public void removeEdge(E verOri, E verDes) {
        // Obtiene los vértices origen y destino
        Vertex<E> vertexOri = getVertex(verOri);
        Vertex<E> vertexDes = getVertex(verDes);
        
        // Verifica que ambos vértices existan
        if (vertexOri != null && vertexDes != null) {
            // Obtiene la primera arista del vértice origen
            Edge<E> edge = vertexOri.listAdj.getFirst();
            // Recorre las aristas del vértice origen
            while (edge != null) {
                // Si encuentra la arista al vértice destino
                if (edge.refDest.equals(vertexDes)) {
                    // Elimina la arista
                    vertexOri.listAdj.remove(edge);
                    break;
                }
                // Avanza a la siguiente arista
                edge = vertexOri.listAdj.getNext();
            }
            
            // Obtiene la primera arista del vértice destino
            edge = vertexDes.listAdj.getFirst();
            // Recorre las aristas del vértice destino
            while (edge != null) {
                // Si encuentra la arista al vértice origen
                if (edge.refDest.equals(vertexOri)) {
                    // Elimina la arista
                    vertexDes.listAdj.remove(edge);
                    break;
                }
                // Avanza a la siguiente arista
                edge = vertexDes.listAdj.getNext();
            }
        }
    }

    // Método para recorrido en profundidad (DFS)
    public void dfs(E startVertex) {
        // Obtiene el vértice de inicio
        Vertex<E> start = getVertex(startVertex);
        // Verifica que el vértice exista
        if (start != null) {
            // Lista para llevar registro de vértices visitados
            ListLinked<Vertex<E>> visited = new ListLinked<>();
            // Llama al método recursivo
            dfsRecursive(start, visited);
            // Imprime salto de línea al final
            System.out.println();
        }
    }

    // Método auxiliar recursivo para DFS
    private void dfsRecursive(Vertex<E> current, ListLinked<Vertex<E>> visited) {
        // Imprime el vértice actual
        System.out.print(current.getData() + " ");
        // Marca el vértice como visitado
        visited.insert(current);
        
        // Obtiene la primera arista del vértice actual
        Edge<E> edge = current.listAdj.getFirst();
        // Recorre todas las aristas del vértice actual
        while (edge != null) {
            // Si el vértice destino no ha sido visitado
            if (!visited.contains(edge.refDest)) {
                // Llama recursivamente a DFS con el vértice destino
                dfsRecursive(edge.refDest, visited);
            }
            // Avanza a la siguiente arista
            edge = current.listAdj.getNext();
        }
    }

    // Método para recorrido en anchura (BFS)
    public void bfs(E startVertex) {
        // Obtiene el vértice de inicio
        Vertex<E> start = getVertex(startVertex);
        // Verifica que el vértice exista
        if (start != null) {
            // Cola para el BFS
            ListLinked<Vertex<E>> queue = new ListLinked<>();
            // Lista para vértices visitados
            ListLinked<Vertex<E>> visited = new ListLinked<>();
            
            // Marca el vértice inicial como visitado
            visited.insert(start);
            // Agrega el vértice inicial a la cola
            queue.insert(start);
            
            // Mientras la cola no esté vacía
            while (!queue.isEmpty()) {
                // Obtiene el primer vértice de la cola
                Vertex<E> current = queue.getFirst();
                // Elimina el vértice de la cola
                queue.removeFirst();
                // Imprime el vértice actual
                System.out.print(current.getData() + " ");
                
                // Obtiene la primera arista del vértice actual
                Edge<E> edge = current.listAdj.getFirst();
                // Recorre todas las aristas del vértice actual
                while (edge != null) {
                    // Si el vértice destino no ha sido visitado
                    if (!visited.contains(edge.refDest)) {
                        // Marca como visitado
                        visited.insert(edge.refDest);
                        // Agrega a la cola
                        queue.insert(edge.refDest);
                    }
                    // Avanza a la siguiente arista
                    edge = current.listAdj.getNext();
                }
            }
            // Imprime salto de línea al final
            System.out.println();
        }
    }



// Encuentra el camino más corto entre 'v' y 'z' usando BFS
// Retorna un ArrayList con la secuencia de vértices del camino
public ArrayList<E> bfsPath(E startVertex, E endVertex) {
    // Lista para almacenar el camino resultante
    ArrayList<E> path = new ArrayList<>();
    
    // Obtiene los vértices de inicio y fin
    Vertex<E> start = getVertex(startVertex);
    Vertex<E> end = getVertex(endVertex);
    
    // Verifica que ambos vértices existan
    if (start == null || end == null) {
        return path; // Retorna lista vacía si no existen
    }
    
    // Estructuras para BFS
    ListLinked<Vertex<E>> queue = new ListLinked<>();
    ListLinked<Vertex<E>> visited = new ListLinked<>();
    
    // Mapa para registrar el predecesor de cada vértice
    HashMap<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
    
    // Inicialización
    visited.insert(start);
    queue.insert(start);
    predecessors.put(start, null); // El inicio no tiene predecesor
    
    // Bandera para indicar si se encontró el destino
    boolean found = false;
    
    // BFS estándar
    while (!queue.isEmpty() && !found) {
        Vertex<E> current = queue.getFirst();
        queue.removeFirst();
        
        // Si llegamos al vértice destino
        if (current.equals(end)) {
            found = true;
            break;
        }
        
        // Procesar vecinos
        Edge<E> edge = current.listAdj.getFirst();
        while (edge != null) {
            Vertex<E> neighbor = edge.refDest;
            
            if (!visited.contains(neighbor)) {
                visited.insert(neighbor);
                predecessors.put(neighbor, current);
                queue.insert(neighbor);
            }
            edge = current.listAdj.getNext();
        }
    }
    
    // Reconstruir el camino si se encontró
    if (found) {
        // Comenzamos desde el final
        Vertex<E> step = end;
        
        // Retrocedemos hasta el inicio
        while (step != null) {
            path.add(0, step.getData()); // Insertamos al inicio para invertir el orden
            step = predecessors.get(step);
        }
    }
    
    return path;
}

// Inserta una arista ponderada entre los vertices v y z con peso w
public void insertEdgeWeight(E verOri, E verDes, int weight) {
    // Verifica que el peso sea un valor positivo
    if (weight < 0) {
        throw new IllegalArgumentException("El peso no puede ser negativo");
    }
    
    // Obtiene los vertices origen y destino
    Vertex<E> vertexOri = getVertex(verOri);
    Vertex<E> vertexDes = getVertex(verDes);
    
    // Verifica que ambos vertices existan
    if (vertexOri != null && vertexDes != null) {
        // Verifica si la arista ya existe
        if (!searchEdge(verOri, verDes)) {
            // Inserta arista en ambos sentidos (grafo no dirigido)
            vertexOri.listAdj.insert(new Edge<E>(vertexDes, weight));
            vertexDes.listAdj.insert(new Edge<E>(vertexOri, weight));
        } else {
            // Si la arista existe, actualiza el peso
            updateEdgeWeight(vertexOri, vertexDes, weight);
            updateEdgeWeight(vertexDes, vertexOri, weight);
        }
    }
}

// Metodo auxiliar para actualizar peso de una arista existente
private void updateEdgeWeight(Vertex<E> source, Vertex<E> target, int weight) {
    // Recorre las aristas del vertice origen
    Edge<E> edge = source.listAdj.getFirst();
    while (edge != null) {
        // Si encuentra la arista al vertice destino
        if (edge.refDest.equals(target)) {
            // Actualiza el peso
            edge.weight = weight;
            break;
        }
        edge = source.listAdj.getNext();
    }
}

// Encuentra el camino mas corto entre v y z usando BFS (para grafos no ponderados)
public ArrayList<E> shortPath(E startVertex, E endVertex) {
    // Lista para almacenar el camino resultante
    ArrayList<E> path = new ArrayList<>();
    
    // Obtiene los vertices de inicio y fin
    Vertex<E> start = getVertex(startVertex);
    Vertex<E> end = getVertex(endVertex);
    
    // Verifica que ambos vertices existan
    if (start == null || end == null) {
        return path; // Retorna lista vacia si no existen
    }
    
    // Estructuras para BFS
    ListLinked<Vertex<E>> queue = new ListLinked<>();
    ListLinked<Vertex<E>> visited = new ListLinked<>();
    
    // Mapa para registrar el predecesor de cada vertice
    HashMap<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
    
    // Inicializacion
    visited.insert(start);
    queue.insert(start);
    predecessors.put(start, null); // El inicio no tiene predecesor
    
    // Bandera para indicar si se encontro el destino
    boolean found = false;
    
    // BFS estandar
    while (!queue.isEmpty() && !found) {
        Vertex<E> current = queue.getFirst();
        queue.removeFirst();
        
        // Si llegamos al vertice destino
        if (current.equals(end)) {
            found = true;
            break;
        }
        
        // Procesar vecinos
        Edge<E> edge = current.listAdj.getFirst();
        while (edge != null) {
            Vertex<E> neighbor = edge.refDest;
            
            if (!visited.contains(neighbor)) {
                visited.insert(neighbor);
                predecessors.put(neighbor, current);
                queue.insert(neighbor);
            }
            edge = current.listAdj.getNext();
        }
    }
    
    // Reconstruir el camino si se encontro
    if (found) {
        // Comenzamos desde el final
        Vertex<E> step = end;
        
        // Retrocedemos hasta el inicio
        while (step != null) {
            path.add(0, step.getData()); // Insertamos al inicio para invertir el orden
            step = predecessors.get(step);
        }
    }
    
    return path;
}

// Verifica si el grafo es conexo (todos los vertices estan conectados)
public boolean isConexo() {
    // Si el grafo esta vacio, se considera conexo
    if (listVertex.isEmpty()) {
        return true;
    }
    
    // Obtiene el primer vertice del grafo
    Vertex<E> start = listVertex.getFirst();
    
    // Realiza un BFS desde el primer vertice
    ListLinked<Vertex<E>> visited = new ListLinked<>();
    ListLinked<Vertex<E>> queue = new ListLinked<>();
    
    visited.insert(start);
    queue.insert(start);
    
    while (!queue.isEmpty()) {
        Vertex<E> current = queue.getFirst();
        queue.removeFirst();
        
        // Recorre todos los vecinos
        Edge<E> edge = current.listAdj.getFirst();
        while (edge != null) {
            Vertex<E> neighbor = edge.refDest;
            if (!visited.contains(neighbor)) {
                visited.insert(neighbor);
                queue.insert(neighbor);
            }
            edge = current.listAdj.getNext();
        }
    }
    
    // Compara el numero de vertices visitados con el total
    return visited.size() == listVertex.size();
}



// Implementa el algoritmo de Dijkstra para encontrar el camino mas corto en grafos ponderados
public Stack<E> Dijkstra(E startVertex, E endVertex) {
    // Crea una pila para almacenar el camino resultante
    Stack<E> pathStack = new Stack<>();
    
    // Obtiene los vertices de inicio y fin
    Vertex<E> start = getVertex(startVertex);
    Vertex<E> end = getVertex(endVertex);
    
    // Verifica que ambos vertices existan
    if (start == null || end == null) {
        return pathStack; // Retorna pila vacia si no existen
    }
    
    // Estructuras para Dijkstra
    HashMap<Vertex<E>, Integer> distances = new HashMap<>();
    HashMap<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
    PriorityQueue<VertexDistance<E>> priorityQueue = new PriorityQueue<>();
    
    // Inicializa distancias
    Vertex<E> current = listVertex.getFirst();
    while (current != null) {
        if (current.equals(start)) {
            distances.put(current, 0);
        } else {
            distances.put(current, Integer.MAX_VALUE);
        }
        predecessors.put(current, null);
        priorityQueue.add(new VertexDistance<>(current, distances.get(current)));
        current = listVertex.getNext();
    }
    
    // Algoritmo principal de Dijkstra
    while (!priorityQueue.isEmpty()) {
        VertexDistance<E> minVertex = priorityQueue.poll();
        current = minVertex.vertex;
        
        // Si llegamos al vertice destino, termina
        if (current.equals(end)) {
            break;
        }
        
        // Procesa todos los vecinos
        Edge<E> edge = current.listAdj.getFirst();
        while (edge != null) {
            Vertex<E> neighbor = edge.refDest;
            int newDist = distances.get(current) + edge.weight;
            
            // Relajacion de arista
            if (newDist < distances.get(neighbor)) {
                distances.put(neighbor, newDist);
                predecessors.put(neighbor, current);
                priorityQueue.add(new VertexDistance<>(neighbor, newDist));
            }
            edge = current.listAdj.getNext();
        }
    }
    
    // Reconstruye el camino desde el final
    Vertex<E> step = end;
    while (step != null) {
        pathStack.push(step.getData());
        step = predecessors.get(step);
    }
    
    return pathStack;
}

// Clase auxiliar para la cola de prioridad en Dijkstra
private static class VertexDistance<E> implements Comparable<VertexDistance<E>> {
    Vertex<E> vertex;
    int distance;
    
    public VertexDistance(Vertex<E> vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }
    
    @Override
    public int compareTo(VertexDistance<E> other) {
        return Integer.compare(this.distance, other.distance);
    }
}

// a) Obtiene el grado de un nodo especifico
    public int gradoNodo(E data) {
        // Obtiene el vertice correspondiente al dato
        Vertex<E> vertex = getVertex(data);
        if (vertex == null) {
            return -1; // Retorna -1 si el vertice no existe
        }
        
        // El grado es igual al numero de aristas adyacentes
        return vertex.listAdj.size();
    }

    // b) Verifica si el grafo es de tipo camino (Px)
    public boolean esCamino() {
        // Un grafo es camino si:
        // 1. Tiene exactamente 2 nodos con grado 1 (extremos)
        // 2. Todos los demas nodos tienen grado 2
        // 3. El numero de aristas es igual a n-1 (donde n es numero de vertices)
        
        int countGrado1 = 0;
        int countGrado2 = 0;
        int totalVertices = listVertex.size();
        
        // Recorre todos los vertices
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            int grado = current.listAdj.size();
            
            if (grado == 1) {
                countGrado1++;
            } else if (grado == 2) {
                countGrado2++;
            } else {
                // Si hay algun nodo con grado diferente a 1 o 2, no es camino
                return false;
            }
            current = listVertex.getNext();
        }
        
        // Debe tener exactamente 2 nodos grado 1 y el resto grado 2
        return countGrado1 == 2 && 
               (countGrado1 + countGrado2) == totalVertices &&
               secEdge.size() == totalVertices - 1;
    }

    // c) Verifica si el grafo es de tipo ciclo (Cx)
    public boolean esCiclo() {
        // Un grafo es ciclo si:
        // 1. Todos los nodos tienen grado 2
        // 2. El numero de aristas es igual al numero de vertices
        
        int totalVertices = listVertex.size();
        if (totalVertices < 3) return false; // Ciclo minimo requiere 3 nodos
        
        // Recorre todos los vertices
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            if (current.listAdj.size() != 2) {
                return false;
            }
            current = listVertex.getNext();
        }
        
        return secEdge.size() == totalVertices;
    }

    // d) Verifica si el grafo es de tipo rueda (Wx)
    public boolean esRueda() {
        // Un grafo es rueda si:
        // 1. Tiene un nodo central conectado a todos los demas (grado n-1)
        // 2. Los demas nodos forman un ciclo (grado 3)
        // 3. Numero de aristas = 2*(n-1)
        
        int totalVertices = listVertex.size();
        if (totalVertices < 4) return false; // Rueda minima W3 tiene 4 nodos
        
        int central = -1;
        int countGrado3 = 0;
        
        // Busca el nodo central (grado n-1)
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            int grado = current.listAdj.size();
            
            if (grado == totalVertices - 1) {
                central++;
            } else if (grado == 3) {
                countGrado3++;
            } else {
                return false;
            }
            current = listVertex.getNext();
        }
        
        // Debe tener exactamente 1 nodo central y n-1 nodos grado 3
        return central == 1 && 
               countGrado3 == totalVertices - 1 &&
               secEdge.size() == 2 * (totalVertices - 1);
    }

    // e) Verifica si el grafo es completo (Kx)
    public boolean esCompleto() {
        // Un grafo es completo si:
        // 1. Todos los nodos tienen grado n-1
        // 2. Numero de aristas = n*(n-1)/2
        
        int totalVertices = listVertex.size();
        
        // Recorre todos los vertices
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            if (current.listAdj.size() != totalVertices - 1) {
                return false;
            }
            current = listVertex.getNext();
        }
        
        return secEdge.size() == totalVertices * (totalVertices - 1) / 2;
    }

    // Metodo auxiliar para obtener todos los vertices como lista
    private List<Vertex<E>> getVerticesAsList() {
        List<Vertex<E>> vertices = new ArrayList<>();
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            vertices.add(current);
            current = listVertex.getNext();
        }
        return vertices;
    }

// ================= REPRESENTACIONES DEL GRAFO ================= //

    // a) Representación formal: listado de vértices y aristas
    public String representacionFormal() {
        StringBuilder sb = new StringBuilder();
        
        // Lista de vértices
        sb.append("V = {");
        Vertex<E> currentVertex = listVertex.getFirst();
        while (currentVertex != null) {
            sb.append(currentVertex.getData());
            currentVertex = listVertex.getNext();
            if (currentVertex != null) sb.append(", ");
        }
        sb.append("}\n");
        
        // Lista de aristas
        sb.append("A = {");
        Map<String, Boolean> addedEdges = new HashMap<>(); // Para evitar duplicados en grafo no dirigido
        
        Vertex<E> v1 = listVertex.getFirst();
        while (v1 != null) {
            Edge<E> edge = v1.listAdj.getFirst();
            while (edge != null) {
                String edgeKey = v1.getData() + "-" + edge.refDest.getData();
                String reverseKey = edge.refDest.getData() + "-" + v1.getData();
                
                if (!addedEdges.containsKey(edgeKey) && !addedEdges.containsKey(reverseKey)) {
                    sb.append("(").append(v1.getData()).append(", ").append(edge.refDest.getData()).append(")");
                    addedEdges.put(edgeKey, true);
                    
                    // Verifica si hay más aristas por agregar
                    Edge<E> temp = edge;
                    edge = v1.listAdj.getNext();
                    if (edge != null || listVertex.getNext() != null) sb.append(", ");
                } else {
                    edge = v1.listAdj.getNext();
                }
            }
            v1 = listVertex.getNext();
        }
        sb.append("}");
        
        return sb.toString();
    }

    // b) Representación por listas de adyacencia
    public String representacionListasAdyacencia() {
        StringBuilder sb = new StringBuilder();
        
        Vertex<E> current = listVertex.getFirst();
        while (current != null) {
            sb.append(current.getData()).append(" -> ");
            
            Edge<E> edge = current.listAdj.getFirst();
            while (edge != null) {
                sb.append(edge.refDest.getData());
                edge = current.listAdj.getNext();
                if (edge != null) sb.append(", ");
            }
            
            sb.append("\n");
            current = listVertex.getNext();
        }
        
        return sb.toString();
    }

    // c) Representación por matriz de adyacencia
    public String representacionMatrizAdyacencia() {
        // Obtiene la lista ordenada de vértices
        List<Vertex<E>> vertices = getVerticesAsList();
        int size = vertices.size();
        
        // Crea matriz de adyacencia
        int[][] matriz = new int[size][size];
        
        // Mapea cada vértice a su índice
        Map<E, Integer> vertexIndexMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            vertexIndexMap.put(vertices.get(i).getData(), i);
        }
        
        // Llena la matriz con las conexiones
        for (int i = 0; i < size; i++) {
            Vertex<E> vertex = vertices.get(i);
            Edge<E> edge = vertex.listAdj.getFirst();
            
            while (edge != null) {
                int j = vertexIndexMap.get(edge.refDest.getData());
                matriz[i][j] = 1;
                matriz[j][i] = 1; // Grafo no dirigido es simétrico
                edge = vertex.listAdj.getNext();
            }
        }
        
        // Construye el string de salida
        StringBuilder sb = new StringBuilder();
        
        // Encabezado con nombres de vértices
        sb.append("   ");
        for (Vertex<E> v : vertices) {
            sb.append(String.format("%3s", v.getData()));
        }
        sb.append("\n");
        
        // Filas de la matriz
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%3s", vertices.get(i).getData()));
            for (int j = 0; j < size; j++) {
                sb.append(String.format("%3d", matriz[i][j]));
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }

    // Método auxiliar para obtener lista ordenada de vértices
  //  private List<Vertex<E>> getVerticesAsList() {
    //    List<Vertex<E>> vertices = new ArrayList<>();
   //     Vertex<E> current = listVertex.getFirst();
    //    while (current != null) {
 //           vertices.add(current);
   //         current = listVertex.getNext();
     //   }
      //  return vertices;
 //   }

    // Método para representar el grafo como String
    @Override
    public String toString() {
        // Retorna la representación en String de la lista de vértices
        return this.listVertex.toString();
    }
}
