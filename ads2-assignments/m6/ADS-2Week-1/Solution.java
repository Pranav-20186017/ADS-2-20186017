import java.util.Arrays;
class PageRank {
    private Digraph digraph;
    private double[] prval;
    private double[] crval;
    PageRank(Digraph graph) {
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
    void updateValues() {
        for (int i = 1; i < 990; i++) {
            for (int j = 0; j < digraph.V(); j++) {
                update(j);
            }
            prval = Arrays.copyOf(crval, crval.length);
        }
    }
    double getPR(int v) {
        return crval[v];
    }
    double update (int v) {
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
    public String toString() {
        String str = "";
        for (int l = 0; l < crval.length; l++) {
            str = str + l + " - " + crval[l] + "\n";
        }
        return str;
    }
}
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
        int vertices = Integer.parseInt(StdIn.readLine());
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
    }
}