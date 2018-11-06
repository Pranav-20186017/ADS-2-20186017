import java.util.Scanner;
import java.util.ArrayList;
/**
 * class for solution.
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
        final int one = 1;
        final int two = 2;
        final int zero = 0;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> station = new ArrayList<>();
        String[] ctr = sc.nextLine().split(" ");
        String[] stnames = sc.nextLine().split(" ");
        for (int j = zero; j < stnames.length; j++) {
            station.add(stnames[j]);
        }
        EdgeWeightedDigraph ewdg =
        new EdgeWeightedDigraph(Integer.parseInt(ctr[zero]));
        for (int i = zero; i < Integer.parseInt(ctr[one]); i++) {
            String[] data = sc.nextLine().split(" ");
            DirectedEdge edg = new DirectedEdge(station.indexOf(data[zero]),
            station.indexOf(data[one]), Double.parseDouble(data[two]));
            DirectedEdge edgn = new DirectedEdge(station.indexOf(data[one]),
            station.indexOf(data[zero]), Double.parseDouble(data[two]));
            ewdg.addEdge(edg);
            ewdg.addEdge(edgn);
        }
        int querynums = Integer.parseInt(sc.nextLine());
        for (int k = zero; k < querynums; k++) {
            String[] query = sc.nextLine().split(" ");
            DijkstraSP dijkobj = new DijkstraSP(ewdg,
            station.indexOf(query[zero]));
            long ans = (long)dijkobj.distTo(station.indexOf(query[one])); 
            System.out.println(ans);
        }
    }
}