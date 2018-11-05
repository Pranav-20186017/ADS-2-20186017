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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(vertices);
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split(" ");
            ewg.addEdge(new Edge(Integer.parseInt(tokens[0]),
                Integer.parseInt(tokens[1]),
                Double.parseDouble(tokens[2])));
        }
        KruskalMST kmst = new KruskalMST(ewg);
        System.out.format("%.5f", kmst.weight());
    }
}