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
     * main fucntion.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        int vertices = Integer.parseInt(
            StdIn.readLine());
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
        WebSearch ws = new WebSearch(pagerank, file);
        while (StdIn.hasNextLine()) {
            String[] q = StdIn.readLine().split("=");
            System.out.println(ws.iAmFeelingLucky(q[1]));
        }
    }
}
