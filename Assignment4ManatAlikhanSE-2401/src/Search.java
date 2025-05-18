import java.util.*;

public class Search<T> {
    protected Set<T> marked;
    protected Map<T, T> edgeTo;
    protected final T source;

    public Search(T source) {
        this.source = source;
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(T v) {
        return marked.contains(v);
    }

    public Iterable<T> pathTo(T v) {
        if (!hasPathTo(v)) return null;

        LinkedList<T> path = new LinkedList<>();
        for (T x = v; !x.equals(source); x = edgeTo.get(x)) {
            path.push(x);
        }

        path.push(source);
        return path;
    }
}
