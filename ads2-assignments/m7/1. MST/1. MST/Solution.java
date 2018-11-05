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
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
        Integer v, w;
        Double weight;
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split(" ");
            v = Integer.parseInt(tokens[0]);
            w = Integer.parseInt(tokens[1]);
            weight = Double.parseDouble(tokens[2]);
            ewg.addEdge(new Edge(v, w, weight));
        }
        KruskalMST kmst = new KruskalMST(ewg);
        System.out.format("%.5f", kmst.weight());
    }
}
