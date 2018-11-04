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
        for (int y = 0; y < prval.length; y++) {
            prval[y] = (1.0 / (digraph.V()));
        }
        for (int z = 0; z < digraph.V(); z++) {
            if (digraph.outdegree(z) == 0) {
                for (int b = 0; b < digraph.V(); b++) {
                    if (b != z) {
                        digraph.addEdge(z, b);
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
        for (int i = 1; i < thousand; i++) {
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
        for (Integer eachadj : digraph.reverse().adj(v)) {
            rank = rank + (prval[eachadj] / digraph.outdegree(eachadj));
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
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * main fucntion.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        int vertices = Integer.parseInt(
            StdIn.readLine());
        Digraph graph = new Digraph(vertices);
        for (int i = 0; i < vertices; i++) {
            String[] edges = StdIn.readLine().split(" ");
            for (int k = 1; k < edges.length; k++) {
                graph.addEdge(Integer.parseInt(edges[0]),
                    Integer.parseInt(edges[k]));
            }
        }
        System.out.println(graph);
        PageRank pagerank = new PageRank(graph);
        System.out.println(pagerank);
        String file = "WebContent.txt";
        WebSearch ws = new WebSearch(pagerank, file);
        while (StdIn.hasNextLine()) {
            String[] q = StdIn.readLine().split("=");
            System.out.println(ws.iAmFeelingLucky(q[1]));
        }
    }
}
