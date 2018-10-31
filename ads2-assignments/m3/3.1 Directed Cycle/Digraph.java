public class Digraph {
	private final int v;
	private int e;
	private Bag<Integer>[] adj;
	public Digraph(final int v1) {
		this.v = v1;
		this.e = 0;
		adj = (Bag<Integer>[]) new Bag[v];
		for (int i = 0; i < v; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	public int vertex() {
		return v;
	}
	public int edges() {
		return e;
	}
	public void addEdge(final int v1, final int w1) {
		adj[v1].add(w1);
		e++;
	}
	public Iterable<Integer> adj(final int v1) {
		return adj[v1];
	}
}