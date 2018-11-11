// import edu.princeton.cs.algs4.Picture;
// import edu.princeton.cs.algs4.Stack;


import java.awt.Color;

public class SeamCarver {
    private Picture picture;
    private double[] distTo;
    private double[] weights;
    private int[] edgeTo;
    private boolean transposed;

    public SeamCarver(Picture pic) {
        if (pic == null) throw new IllegalArgumentException("picture is null");
        this.picture = new Picture(pic);
        this.transposed = false;
    }

    public Picture picture() {
        return new Picture(picture);
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    public double energy(int x, int y) {
        validateCoordinates(x, y);
        if (isEdgePixel(x, y)) return 1000;
        return Math.sqrt(xGradient(x, y) + yGradient(x, y));
    }

    public int[] findHorizontalSeam() {
        if (!transposed) {
            transpose();
        }
        return findSeam();
    }

    private void validateCoordinates(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            throw new IllegalArgumentException("x must be less than "
                    + width() + " and y must be less than " + height());
        }
    }



    public int[] findVerticalSeam() {
        if (transposed) {
            transpose();
        }
        return findSeam();
    }

    public void removeHorizontalSeam(int[] seam) {
        if (!transposed) {
            transpose();
        }
        removeVerticalSeam(seam);
        transpose();
    }

    public void removeVerticalSeam(int[] seam) {
        if (seam == null) throw new IllegalArgumentException("Given seam is null");
        if (seam.length != height()) throw new IllegalArgumentException("Seam is in invalid size");
        for (int e: seam)
            if (e < 0 || e >= width()) throw new IllegalArgumentException("Indices must be in given range");
        for (int i = 1; i < seam.length; i++)
            if (Math.abs(seam[i] - seam[i-1]) > 1)
                throw new IllegalArgumentException("Invalid seam");

        int width = width() - 1;
        int height = height();
        Color[][] colors = colors(picture);
        Picture resizedPicture = new Picture(width, height);

        // set each row to original row's Colors minus seam pixel
        for (int row = 0; row < height; row++) {
            int seamCol = seam[row];

            Color[] original = colors[row];
            Color[] seamRemoved = new Color[width];

            System.arraycopy(original, 0, seamRemoved, 0, seamCol);
            System.arraycopy(original, seamCol + 1,
                    seamRemoved, seamCol, width - seamCol);

            for (int col = 0; col < width; col++) {
                resizedPicture.set(col, row, seamRemoved[col]);
            }
        }

        this.picture = resizedPicture;
    }

    private Color[][] colors(Picture pic) {
        Color[][] result = new Color[height()][width()];
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                result[row][col] = pic.get(col, row);
            }
        }
        return result;
    }

    private boolean isEdgePixel(int col, int row) {
        return (col == 0 || col == (width() - 1) || row == 0 || row == (height() - 1));
    }

    private double gradient(Color color1, Color color2) {
        int r, g, b;
        r = Math.abs(color1.getRed() - color2.getRed());
        g = Math.abs(color1.getGreen() - color2.getGreen());
        b = Math.abs(color1.getBlue() - color2.getBlue());
        return r * r + g * g + b * b;
    }

    private double xGradient(int x, int y) {
        Color color1, color2;
        color1 = getColor(x - 1, y);
        color2 = getColor(x + 1, y);
        return gradient(color1, color2);
    }

    private double yGradient(int x, int y) {
        Color color1, color2;
        color1 = getColor(x, y - 1);
        color2 = getColor(x, y + 1);
        return gradient(color1, color2);
    }

    private void initialise() {
        int size = width() * height();
        weights = new double[size];
        distTo = new double[size];
        edgeTo = new int[size];

        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                int v = coordinateToVertex(col, row);
                weights[v] = energy(col, row);
                edgeTo[v] = -1;
                if (row == 0) {
                    distTo[v] = weights[v];
                } else {
                    distTo[v] = Double.POSITIVE_INFINITY;
                }
            }
        }

    }

    private int coordinateToVertex(int col, int row) {
        return (row * width()) + col;
    }

    private void relax(int v, int w) {
        if (distTo[w] > distTo[v] + weights[w]) {
            distTo[w] = distTo[v] + weights[w];
            edgeTo[w] = v;
        }
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width() && y >= 0 && y < height();
    }

    private Iterable<Integer> adj(int col, int row) {
        Stack<Integer> adj = new Stack<Integer>();

        int x = col - 1;
        int y = row + 1;
        int w;
        if (isValidCoordinate(x, y)) {
            w = coordinateToVertex(x, y);
            adj.push(w);
        }

        x = col + 1;
        y = row + 1;
        if (isValidCoordinate(x, y)) {
            w = coordinateToVertex(x, y);
            adj.push(w);
        }

        x = col;
        y = row + 1;
        if (isValidCoordinate(x, y)) {
            w = coordinateToVertex(x, y);
            adj.push(w);
        }
        return adj;
    }

    private void relaxEdges() {
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                int v = coordinateToVertex(col, row);
                for (int w: adj(col, row)) {
                    relax(v, w);
                }
            }
        }
    }

    private int shortestPathLastVertex() {
        double min = Double.POSITIVE_INFINITY;
        int lastRow = height() - 1;
        int lastVertex = -1;
        for (int col = 0; col < width(); col++) {
            int v = coordinateToVertex(col, lastRow);
            if (min > distTo[v]) {
                min = distTo[v];
                lastVertex = v;
            }
        }
        return lastVertex;
    }

     private int[] shortestPath(int lastVertex) {
        int[] path = new int[height()];
        int col = height() - 1;
        for (int v = lastVertex; v >= 0; v = edgeTo[v]) {
            path[col] = v % width();
            col--;
        }
        if (path.length > 1)
            path[0] = path[1];
        return path;
    }

    private void transpose() {
        Picture tPicture = new Picture(height(), width());
        for (int row = 0; row < tPicture.height(); row++)
            for (int col = 0; col < tPicture.width(); col++)
                tPicture.set(col, row, getColor(row, col));

        picture = tPicture;
        transposed = !transposed;
    }

    private int[] findSeam() {
        initialise();
        relaxEdges();
        int[] path = shortestPath(shortestPathLastVertex());

        if (transposed) {
            transpose();
        }
        weights = null;
        distTo = null;
        edgeTo = null;
        return path;
    }

    private Color getColor(int col, int row) {
        return picture.get(col, row);
    }

}