import java.util.Scanner;
import java.util.Arrays;
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
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
        int count = edges;
        while (count > 0) {
            String[] tokens = sc.nextLine().split(" ");
            int v = Integer.parseInt(tokens[0]);
            int w = Integer.parseInt(tokens[1]);
            int cost = Integer.parseInt(tokens[2]);
            Edge ed = new Edge(v, w, cost);
            ewg.addEdge(ed);
            count--;
        }
        String caseToGo = sc.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            System.out.println(ewg);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] path = sc.nextLine().split(" ");
            int source = Integer.parseInt(path[0]);
            int destination = Integer.parseInt(path[1]);
            DijkstraUndirectedSP sp = new DijkstraUndirectedSP(ewg, source);
            if (sp.hasPathTo(destination)) {
                System.out.println(sp.distTo(destination));
            } else {
                System.out.println("No Path Found.");
            }
            break;
        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and
            // second is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] vpath = sc.nextLine().split(" ");
            source = Integer.parseInt(vpath[0]);
            int via = Integer.parseInt(vpath[1]);
            destination = Integer.parseInt(vpath[vpath.length - 1]);
            DijkstraUndirectedSP dsp
                = new DijkstraUndirectedSP(ewg, source);
            if (dsp.hasPathTo(destination)) {
                Queue<Integer> que = new Queue<Integer>();
                for (Edge e : dsp.pathTo(via)) {
                    int vertex = e.either();
                    int other = e.other(vertex);
                    int v = 0;
                    int w = 0;
                    for (Integer j : que) {
                        if (vertex == j) {
                            v = 1;
                        }
                        if (other == j) {
                            w = 1;
                        }

                    }
                    if (w == 0) {
                        que.enqueue(other);
                    }
                    if (v == 0) {
                        que.enqueue(vertex);
                    }
                }
                DijkstraUndirectedSP two
                    = new DijkstraUndirectedSP(ewg, via);
                for (Edge e : two.pathTo(destination)) {
                    int vertex = e.either();
                    int other = e.other(vertex);
                    int v = 0;
                    int w = 0;
                    for (Integer j : que) {
                        if (vertex == j) {
                            v = 1;
                        }
                        if (other == j) {
                            w = 1;
                        }
                    }
                    if (v == 0) {
                        que.enqueue(vertex);
                    }
                    if (w == 0) {
                        que.enqueue(other);
                    }
                }
                System.out.println(dsp.distTo(via) + two.distTo(destination));
                while (!que.isEmpty()) {
                    System.out.print(que.dequeue() + " ");
                }
            } else {
                System.out.println("No Path Found.");
            }
            break;
        default:
            break;
        }
    }
}
