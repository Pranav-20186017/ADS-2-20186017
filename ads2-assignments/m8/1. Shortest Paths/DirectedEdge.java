/**
 * Class for directed edge.
 */
public class DirectedEdge {
    /**
     * vertex v.
     */
    private final int v;
    /**
     * vertex w.
     */
    private final int w;
    /**
     * weight of the edge.
     */
    private final double weight;
    /**
     * Initializes a directed edge from vertex {@code v} to vertex {@code w}
     * the given {@code weight}.
     * @param source the tail vertex
     * @param destination the head vertex
     * @param heavy the weight of the directed edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *    is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public DirectedEdge(final int source,
        final int destination, final double heavy) {
        this.v = source;
        this.w = destination;
        this.weight = heavy;
    }
    /**
     * Returns the tail vertex of the directed edge.
     * @return the tail vertex of the directed edge
     */
    public int from() {
        return v;
    }
    /**
     * Returns the head vertex of the directed edge.
     * @return the head vertex of the directed edge
     */
    public int to() {
        return w;
    }
    /**
     * Returns the weight of the directed edge.
     * @return the weight of the directed edge
     */
    public double weight() {
        return weight;
    }
    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
