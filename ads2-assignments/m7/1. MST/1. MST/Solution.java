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
            ewg.addEdge(new Edge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Double.parseDouble(tokens[2])));
            KruskalMST kmst = new KruskalMST(ewg);
            System.out.format("%.5f",kmst.weight());
        }
        
    }

}