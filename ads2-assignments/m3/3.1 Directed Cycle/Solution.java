import java.util.Scanner;
class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private boolean[] onStack;
	private Stack<Integer> cycle;
	DirectedCycle(final Digraph g1) {
		marked = new boolean[g1.vertex()];
		onStack = new boolean[g1.vertex()];
		edgeTo = new int[g1.vertex()];
		for (int v = 0; v < g1.vertex(); v++) {
			if(!marked[v] && cycle == null) {
				dfs(g1, v);
			}
		}
	}
	private void dfs(final Digraph g2, final int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w : g2.adj(v)) {
			if (cycle != null) {
				return;
			} else if (!marked[w]) {
				edgeTo[w] = v;
				dfs(g2, w);
			} else if (onStack[w]) {
				cycle = new Stack<Integer>();
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}
	public boolean hasCycle() {
		return cycle != null;
	}
}
final class Solution {
	private Solution() {
		//unused.
	}
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertexes = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		Digraph graphobj = new Digraph(vertexes);
		while (scan.hasNext()) {
			String[] lines = scan.nextLine().split(" ");
graphobj.addEdge(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
		}
		DirectedCycle finder = new DirectedCycle(graphobj);
		if (finder.hasCycle()) {
			System.out.println("Cycle exists.");
		} else {
			System.out.println("Cycle doesn't exists.");
		}
	}
}