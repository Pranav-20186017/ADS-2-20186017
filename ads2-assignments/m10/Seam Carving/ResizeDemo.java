import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
/**
 * Class for resize demo.
 */
public class ResizeDemo {
    /**
     * Constructs the object.
     */
    private ResizeDemo() {
        //unused.
    }
    /**
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        final int three = 3;
        if (args.length != three) {
            return;
        }
        Picture inputImg = new Picture(args[0]);
        int removeColumns = Integer.parseInt(args[1]);
        int removeRows = Integer.parseInt(args[2]); 
        StdOut.printf("image is %d columns by %d rows\n",
            inputImg.width(), inputImg.height());
        SeamCarver sc = new SeamCarver(inputImg);
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < removeRows; i++) {
            int[] horizontalSeam = sc.findHorizontalSeam();
            sc.removeHorizontalSeam(horizontalSeam);
        }
        for (int i = 0; i < removeColumns; i++) {
            int[] verticalSeam = sc.findVerticalSeam();
            sc.removeVerticalSeam(verticalSeam);
        }
        Picture outputImg = sc.picture();
        StdOut.printf("new image size is %d columns by %d rows\n",
            sc.width(), sc.height());
        StdOut.println("Resizing time: " + sw.elapsedTime() + " seconds.");
        inputImg.show();
        outputImg.show();
    }  
}