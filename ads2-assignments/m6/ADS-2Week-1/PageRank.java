import java.util.Arrays;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * object for the digraph class.
     */
    private Digraph digraph;
    /**
     * container to hold values of rank from previous iteration.
     */
    private double[] prval;
    /**
     * container to hold updated values of rank after each iteration.
     */
    private double[] crval;
    /**
     * Constructs the object.
     *
     * @param      graph  The graph
     */
    PageRank(final Digraph graph) {
        this.digraph = graph;
        prval = new double[digraph.V()];
        for (int i = 0; i < prval.length; i++) {
            prval[i] = (1.0 / (digraph.V()));
        }
        for (int j = 0; j < digraph.V(); j++) {
            if (digraph.outdegree(j) == 0) {
                for (int k = 0; k < digraph.V(); k++) {
                    if (k != j) {
                        digraph.addEdge(j, k);
                    }
                }
            }
        }
        crval = new double[digraph.V()];
        updateValues();
    }
    /**
     * updates values and resizes the array.
     */
    void updateValues() {
        final int thousand = 1000;
        for (int i = 0; i < thousand; i++) {
            for (int j = 0; j < digraph.V(); j++) {
                update(j);
            }
            prval = Arrays.copyOf(crval, crval.length);
        }
    }
    /**
     * Gets the page rank.
     *
     * @param      v     { no of vertices in the graph }.
     *
     * @return     The page rank.
     */
    double getPR(final int v) {
        return crval[v];
    }
    /**
     * updates the values in cr after each iteration.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    double update(final int v) {
        double rank = 0.0;
        if (digraph.indegree(v) == 0) {
            crval[v] = 0.0;
            return crval[v];
        }
        for (Integer itr : digraph.reverse().adj(v)) {
            rank = rank + (prval[itr] / digraph.outdegree(itr));
        }
        crval[v] = rank;
        return crval[v];
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String str = "";
        for (int l = 0; l < crval.length; l++) {
            str = str + l + " - " + crval[l] + "\n";
        }
        return str;
    }
}