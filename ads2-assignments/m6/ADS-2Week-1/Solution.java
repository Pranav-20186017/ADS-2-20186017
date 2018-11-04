import java.util.Arrays;
import java.util.Scanner;
class PageRank {
    private Digraph digraph;
    private double[] prval;
    private double[] crval;
    PageRank(Digraph graph) {
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
        for (Integer itr : digraph.reverse().adj(v)) {
            rank = rank + (prval[itr] / digraph.outdegree(itr));
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
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        Digraph graph = new Digraph(vertices);
        for (int i = 0; i < vertices; i++) {
            String line = sc.nextLine();
            String[] edges = line.split(" ");
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