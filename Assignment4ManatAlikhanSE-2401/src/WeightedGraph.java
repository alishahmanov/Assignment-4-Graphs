import java.util.*;

public class WeightedGraph<T> {
    private final boolean undirected;
    private final Map<T, Vertex<T>> vertices;

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.vertices = new HashMap<>();
    }

    public void addEdge(T sourceData, T destData, double weight) {
        Vertex<T> source = getOrCreateVertex(sourceData);
        Vertex<T> dest = getOrCreateVertex(destData);

        source.addAdjacentVertex(dest, weight);

        if (undirected) {
            dest.addAdjacentVertex(source, weight);
        }
    }

    public void addVertex(T data) {
        getOrCreateVertex(data);
    }

    public boolean hasVertex(T data) {
        return vertices.containsKey(data);
    }

    public Vertex<T> getVertex(T data) {
        return vertices.get(data);
    }

    public Set<T> getVertices() {
        return vertices.keySet();
    }

    public Set<T> adjacencyList(T data) {
        Vertex<T> vertex = vertices.get(data);
        if (vertex == null) return Collections.emptySet();

        Set<T> neighbors = new HashSet<>();
        for (Vertex<T> v : vertex.getAdjacentVertices().keySet()) {
            neighbors.add(v.getData());
        }

        return neighbors;
    }

    public double getWeight(T from, T to) {
        Vertex<T> fromVertex = vertices.get(from);
        Vertex<T> toVertex = vertices.get(to);
        if (fromVertex == null || toVertex == null) {
            throw new IllegalArgumentException("Invalid vertex");
        }

        return fromVertex.getAdjacentVertices().getOrDefault(toVertex, Double.MAX_VALUE);
    }

    private Vertex<T> getOrCreateVertex(T data) {
        if (!vertices.containsKey(data)) {
            vertices.put(data, new Vertex<>(data));
        }
        return vertices.get(data);
    }
}
