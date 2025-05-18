import java.util.*;

public class DijkstraSearch<T> extends Search<T> {
    private final Map<T, Double> distances;
    private final Set<T> unsettled;
    private final WeightedGraph<T> graph;

    public DijkstraSearch(WeightedGraph<T> graph, T source) {
        super(source);
        this.graph = graph;
        this.distances = new HashMap<>();
        this.unsettled = new HashSet<>();

        dijkstra();
    }

    private void dijkstra() {
        distances.put(source, 0.0);
        unsettled.add(source);

        while (!unsettled.isEmpty()) {
            T current = getMinimumDistanceVertex(unsettled);
            unsettled.remove(current);
            marked.add(current);

            for (T neighbor : graph.adjacencyList(current)) {
                if (!marked.contains(neighbor)) {
                    double currentDistance = distances.get(current);
                    double edgeWeight = graph.getWeight(current, neighbor);
                    double totalDistance = currentDistance + edgeWeight;

                    if (totalDistance < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
                        distances.put(neighbor, totalDistance);
                        edgeTo.put(neighbor, current);
                        unsettled.add(neighbor);
                    }
                }
            }
        }
    }

    private T getMinimumDistanceVertex(Set<T> vertices) {
        T minVertex = null;
        double minDistance = Double.MAX_VALUE;

        for (T v : vertices) {
            double dist = distances.getOrDefault(v, Double.MAX_VALUE);
            if (dist < minDistance) {
                minDistance = dist;
                minVertex = v;
            }
        }

        return minVertex;
    }
}
