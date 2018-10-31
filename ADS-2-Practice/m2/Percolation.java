class Percolation {
	private boolean[][] grid;
	private int size;
	private Graph list;
	public Percolation(int s) {
		this.size = s;
		grid = new boolean[size][size];
		list = new Graph(size * size + 2);
	}
	public void open(int row, int col) {
		grid[row][col] = true;
		if (row == 0) {
			list.addEdge(size * size, percolates(row, col));
		}
	}
}