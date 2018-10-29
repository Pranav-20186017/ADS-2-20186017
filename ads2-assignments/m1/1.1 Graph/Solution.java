import java.util.Arrays;
import java.util.Scanner;
interface Graph {
    public int V();
    public int E();
    public void addEdge(int v, int w);
    public Iterable<Integer> adj(int v);
    public boolean hasEdge(int v, int w);
}
class Matrix implements Graph {
    int vertices;
    int edges;
    int[][] matrix;
    Matrix(int v, int e) {
        this.vertices = v;
        this.edges = 0;
        matrix = new int[v][v];
    }
    public int V() {
        return vertices;
    }
    public int E() {
        return edges;
    }
    public void addEdge(int v, int w) {
        if (v != w && !hasEdge(v, w)) {
            matrix[v][w] = 1;
            matrix[w][v] = 1;
            edges++;
        }

    }
    public Iterable<Integer> adj(int v) {
        return null;
    }
    public boolean hasEdge(int v, int w) {
        return matrix[v][w] == 1;
    }
}
public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String adjacency = scan.nextLine();
        int vertices = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        switch (adjacency) {
        case "List" :
            break;
        case "Matrix" :
            Matrix matrix = new Matrix(vertices, edges);
            String[] elements = scan.nextLine().split(",");
            while (scan.hasNext()) {
                String[] edge = scan.nextLine().split(" ");
                matrix.addEdge(Integer.parseInt(edge[0]), Integer.parseInt(edge[1]));
            }
            System.out.println(matrix.vertices + " vertices, " + matrix.edges + " edges");
            if (matrix.vertices <= 1 || matrix.edges <= 1) {
                System.out.println("No edges");
                return;
            }
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    System.out.print(matrix.matrix[i][j]  + " ");
                }
                System.out.println();
            }
            break;
        default:
        }
    }
}