/**
 * Class for kruskal mst.
 */
final class KruskalMST {
    /**
     * variable for increment.
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**
     * varible for weight.
     */
    private double weight;                        // weight of MST
    /**
     * queue of edges.
     */
    private Queue<Edge> mst = new Queue<Edge>();  // edges in MST
    /**
     * Compute a minimum spanning tree (or forest)
     * of an edge-weighted graph.
     * @param graph the edge-weighted graph
     */
    protected KruskalMST(final EdgeWeightedGraph graph) {
        // more efficient to build heap by passing array of edges
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : graph.edges()) {
            pq.insert(e);
        }
        // run greedy algorithm
        UF uf = new UF(graph.V());
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.weight();
            }
        }
        // check optimality conditions
        assert check(graph);
    }
    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }
    /**
     *
     * @return the sum of the edge weights
     * in a minimum spanning tree (or forest)
     */
    public double weight() {
        return weight;
    }
    /**
     * check for log*V.
     *
     * @param      graph     { graph object }
     *
     * @return     { description_of_the_return_value }
     */
    private boolean check(final EdgeWeightedGraph graph) {
        // check total weight
        double total = 0.0;
        for (Edge e : edges()) {
            total += e.weight();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            return false;
        }
        // check that it is acyclic
        UF uf = new UF(graph.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }
        // check that it is a spanning forest
        for (Edge e : graph.edges()) {
            int v = e.either(), w = e.other(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }
        for (Edge e : edges()) {
            // all edges in MST except e
            uf = new UF(graph.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }
            // check that e is min weight edge in crossing cut
            for (Edge f : graph.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge "
                            + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
