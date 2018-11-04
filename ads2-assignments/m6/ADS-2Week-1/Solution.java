import java.util.Scanner;
import java.util.Arrays;
class PageRank {
    private Digraph digraph;
    private double[] prval;
    private double[] crval;
    void updatePageRankValues() {
        for (int l = 1; l < 1000; l++) {
            for (int m = 0; m < digraph.V(); m++) {
                update(m);
            }
            prval = Arrays.copyOf(crval, crval.length);
        }
    }
    PageRank(Digraph graph) {
        this.digraph = graph;
        prval = new double[digraph.V()];
        for (int i = 0; i < prval.length; i++) {
            prval[i] = (1.0 / digraph.V());
        }
        for (int j = 0; j < digraph.V(); j++) {
            if (digraph.outdegree(j) == 0) {
                for (int k = 0; k < digraph.V(); k++) {
                    if (j != k) {
                        digraph.addEdge(j, k);
                    }
                }
            }
        }
        crval = new double[digraph.V()];
        updatePageRankValues();
    }
    double getPR(int v) {
        return crval[v];
    }
    double update(int v) {
        double pgrank = 0.0;
        if (digraph.indegree(v) == 0) {
            crval[v] = 0.0;
            return crval[v];
        }
        for (int itr : digraph.reverse().adj(v)) {
            pgrank += (prval[itr] / digraph.outdegree(itr));
        }
        crval[v] = pgrank;
        return crval[v];
    }
    public String toString() {
        String str = "";
            for (int i = 0; i < crval.length; i++) {
                str = str + i + " - " + crval[i] + "\n";
            }
            return str;
    }
}
class WebSearch {

}
class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused.
    }
    /**
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        Digraph digraph = new Digraph(vertices);
        for (int i = 0; i < vertices; i++) {
            String line = sc.nextLine();
            String[] edge = line.split(" ");
            for (int j = 1; j < edge.length; j++) {
                digraph.addEdge(Integer.parseInt(edge[0]),
                    Integer.parseInt(edge[j]));
            }
        }
        System.out.println(digraph);
        PageRank pagerank = new PageRank(digraph);
        System.out.println(pagerank);
        String file = "WebContent.txt";
    }
}