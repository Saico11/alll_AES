import java.util.ArrayList;

public class GraphListEdgeDirigido<V,E> extends GraphListEdge<V,E> {

    @Override
    public void insertEdge(V verOri, V verDes, E info) {
        VertexObj<V,E> vertexOri = getVertex(verOri);
        VertexObj<V,E> vertexDes = getVertex(verDes);
        
        if (vertexOri != null && vertexDes != null) {
            if (!searchEdgeDirigido(verOri, verDes)) {
                EdgeObj<V,E> newEdge = new EdgeObj<>(vertexOri, vertexDes, info, secEdge.size());
                secEdge.add(newEdge);
            }
        }
    }

    public boolean searchEdgeDirigido(V verOri, V verDes) {
        VertexObj<V,E> vertexOri = getVertex(verOri);
        VertexObj<V,E> vertexDes = getVertex(verDes);
        
        if (vertexOri != null && vertexDes != null) {
            for (EdgeObj<V,E> edge : secEdge) {
                if (edge.endVertex1.equals(vertexOri) && edge.endVertex2.equals(vertexDes)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Métodos para grados en representación por lista de aristas
    public int gradoSalida(V data) {
        int count = 0;
        VertexObj<V,E> vertex = getVertex(data);
        if (vertex == null) return -1;
        
        for (EdgeObj<V,E> edge : secEdge) {
            if (edge.endVertex1.equals(vertex)) {
                count++;
            }
        }
        return count;
    }

    public int gradoEntrada(V data) {
        int count = 0;
        VertexObj<V,E> vertex = getVertex(data);
        if (vertex == null) return -1;
        
        for (EdgeObj<V,E> edge : secEdge) {
            if (edge.endVertex2.equals(vertex)) {
                count++;
            }
        }
        return count;
    }
}
