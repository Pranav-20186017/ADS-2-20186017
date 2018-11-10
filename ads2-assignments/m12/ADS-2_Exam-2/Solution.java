import java.util.Scanner;
public class Solution {

    public static void main(String[] args) {
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
            Edge ed = new Edge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
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
            int s = Integer.parseInt(path[0]);
            int d = Integer.parseInt(path[1]);
            DijkstraUndirectedSP sp = new DijkstraUndirectedSP(ewg, s);
            if (sp.hasPathTo(d)) {
                System.out.println(sp.distTo(d));
            } else {
                System.out.println("No Path Found.");
            }
            break;
        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            break;

        default:
            break;
        }

    }
}