import java.util.*;

public class BreadthFirstSearch<T> extends Search<T> {

    public BreadthFirstSearch(WeightedGraph<T> graph, T source) {
        super(source);
        bfs(graph, source);
    }

    private void bfs(WeightedGraph<T> graph, T start) {
        Queue<T> queue = new LinkedList<>();
        marked.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();

            for (T neighbor : graph.adjacencyList(current)) {
                if (!marked.contains(neighbor)) {
                    marked.add(neighbor);
                    edgeTo.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
    }
}
