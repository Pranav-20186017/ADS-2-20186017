import java.util.Scanner;
public class Solution {

	public static void main(String[] args) {
		// Self loops are not allowed...
		// Parallel Edges are allowed...
		// Take the Graph input here...
		Scanner sc = new Scanner(System.in);
		int vertices = sc.nextInt();
		int edges = sc.nextInt();
		for (int i = 0; i < edges; i++) {
			String line = sc.nextLine();
			String[] tokens = line.split(" ");
			int v = Integer.parseInt(tokens[0]);
			int w = Integer.parseInt(tokens[1]);
			int cost = Integer.parseInt(tokens[2]);
			DirectedEdge e = new DirectedEdge(v, w, cost);
			EdgeWeightedDigraph ed = new EdgeWeightedDigraph(vertices);
			ed.addEdge(e);
		}
		String caseToGo = sc.nextLine();
		switch (caseToGo) {
		case "Graph":
			EdgeWeightedDigraph d = new EdgeWeightedDigraph(vertices);
			System.out.println(d);
			break;

		case "DirectedPaths":
			// Handle the case of DirectedPaths, where two integers are given.
			// First is the source and second is the destination.
			// If the path exists print the distance between them.
			// Other wise print "No Path Found."
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