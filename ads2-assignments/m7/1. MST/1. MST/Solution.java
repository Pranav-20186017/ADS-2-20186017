import java.util.Scanner;
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
     * main function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices, edges);
        while(sc.hasNext()) {
            String line = sc.nextLine();
            String[] tokens = line.split(" ");
            int v = Integer.parseInt(tokens[0]);
            int w = Integer.parseInt(tokens[1]);
            double weight = Double.parseDouble(tokens[2]);
            ewg.addEdge(new Edge(v, w, weight));
            KruskalMST kmst = new KruskalMST(ewg);
            System.out.format("%.5f",kmst.weight());
        }
        
    }

}