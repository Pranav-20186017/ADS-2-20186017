import java.util.Scanner;
import java.util.Arrays;
/**
Class Solution.
*/
public final class Solution {
    /**
     * Main.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // read the first line of the tokens to get the number of vertices
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        String[] tokens;
        Digraph digraph = new Digraph(vertices);
        Digraph aux = new Digraph(vertices);
        // iterate count of vertices times
        for (int i = 0; i < vertices; i++) {
            tokens = sc.nextLine().split(" ");
            if (tokens.length >= 2) {
                for (int j = 1; j < tokens.length; j++) {
                    digraph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[j]));
                    aux.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[j]));
                }
            } else {
                for (int h = 0; (h < vertices); h++) {
                    if (h == i) {
                        continue;
                    } else {
                        aux.addEdge(Integer.parseInt(tokens[0]), h);
                    }
                }
            }
        }
        System.out.println(digraph);
        PageRank pagerank = new PageRank(aux);
    }
}
class PageRank {
    /**
     * Private Value.
     */
    private Digraph digraph;
    /**
     * Private Value.
     */
    private int vertices;
    /**
     * Private Value.
     */
    private double[] ranklist;
    /**
     * Private Value.
     */
    private double[] finalranks;
    /**
     * Private Value.
     */
    private double temp;
    /**
     * Constructs the object.
     *
     * @param      d     { parameter_description }
     */
    protected PageRank(final Digraph d) {
        digraph = d;
        vertices = digraph.V();
        ranklist = new double[vertices];
        finalranks = new double[vertices];
        for (int i = 0; i < vertices; i++) {
            ranklist[i] = (1 / (double)(vertices));
        }
        Digraph revdigraph = digraph.reverse();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < vertices; j++) {
                temp = 0.0;
                for (int k : revdigraph.adj(j)) {
                    // System.out.println(k);
                    temp += ((ranklist[k]) / ((double)(digraph.outdegree(k))));
                    // System.out.println(j);
                    // System.out.println(temp);
                }
                finalranks[j] = temp;
                // System.out.println(finalranks[j]);
                // System.out.println("one sublist is done");
            }
            if (Arrays.equals(ranklist, finalranks)) {
                break;
            } else {
                ranklist = finalranks.clone();
            }
            // System.out.println("iteration done");
        }
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " - " + finalranks[i] + "\n");
        }
    }
}