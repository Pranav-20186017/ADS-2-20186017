import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
    private Picture picture;
    private double[] distTo;
    private double[] weights;
    private int[] edgeTo;
    private boolean transposed;
    /**
     * Constructs the object.
     *
     * @param      pic   The picture
     */
    public SeamCarver(Picture pic) {
        if (pic == null) {
            throw new IllegalArgumentException("picture is null");
        }
        this.picture = new Picture(pic);
        this.transposed = false;
    }
    /**
     * returns the picture.
     *
     * @return     { description_of_the_return_value }
     */
    public Picture picture() {
        return new Picture(picture);
    }
    /**
     * returns width of the picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int width() {
        return picture.width();
    }
    /**
     * returns height of the picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int height() {
        return picture.height();
    }
    /**
     * Gets the color.
     *
     * @param      col   The col
     * @param      row   The row
     *
     * @return     The color.
     */
    private Color getColor(int col, int row) {
        return picture.get(col, row);
    }
    /**
     * computes the enerrgy of a given co-ordinate.
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double energy(int x, int y) {
        validateCoordinates(x, y);
        if (isEdgePixel(x, y)) return 1000;
        return Math.sqrt(xGradient(x, y) + yGradient(x, y));
    }
    /**
     * finds the horizontal seam.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findHorizontalSeam() {
        if (!transposed) {
            transpose();
        }
        return findSeam();
    }
    /**
     * validates given co-ordinates.
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     */
    private void validateCoordinates(int x, int y) {
        if (!isValidCoordinate(x, y)) {
            throw new IllegalArgumentException("x must be less than "
                    + width() + " and y must be less than " + height());
        }
    }
    /**
     * finds the vertical seam in a given picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findVerticalSeam() {
        if (transposed) {
            transpose();
        }
        return findSeam();
    }
    /**
     * Removes a horizontal seam.
     *
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(int[] seam) {
        if (!transposed) {
            transpose();
        }
        removeVerticalSeam(seam);
        transpose();
    }
    /**
     * Removes a vertical seam.
     *
     * @param      seam  The seam
     */
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("Given seam is null");
        }
        if (seam.length != height()) {
            throw new IllegalArgumentException("Seam is in invalid size");
        }
        for (int e: seam)
            if (e < 0 || e >= width()) {
                String s = "Indices must be in given range";
                throw new IllegalArgumentException(s);
            }
        for (int i = 1; i < seam.length; i++)
            if (Math.abs(seam[i] - seam[i-1]) > 1) {
                throw new IllegalArgumentException("Invalid seam");
            }
        int width = width() - 1;
        int height = height();
        Color[][] colors = colors(picture);
        Picture resizedPicture = new Picture(width, height);
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
    /**
     * fetches the colors given a picture.
     *
     * @param      pic   The picture
     *
     * @return     { description_of_the_return_value }
     */
    private Color[][] colors(Picture pic) {
        Color[][] result = new Color[height()][width()];
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                result[row][col] = pic.get(col, row);
            }
        }
        return result;
    }
    /**
     * Determines if edge pixel.
     *
     * @param      col   The col
     * @param      row   The row
     *
     * @return     True if edge pixel, False otherwise.
     */
    private boolean isEdgePixel(int col, int row) {
        return (col == 0 || col == (width() - 1)
            || row == 0 || row == (height() - 1));
    }
    /**
     * computes the gradinet of two colors.
     *
     * @param      color1  The color 1
     * @param      color2  The color 2
     *
     * @return     { description_of_the_return_value }
     */
    private double gradient(Color color1, Color color2) {
        int r, g, b;
        r = Math.abs(color1.getRed() - color2.getRed());
        g = Math.abs(color1.getGreen() - color2.getGreen());
        b = Math.abs(color1.getBlue() - color2.getBlue());
        return r * r + g * g + b * b;
    }
    /**
     * calculates the x gradient.
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private double xGradient(int x, int y) {
        Color color1, color2;
        color1 = getColor(x - 1, y);
        color2 = getColor(x + 1, y);
        return gradient(color1, color2);
    }
    /**
     * computes the y gradient.
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    private double yGradient(int x, int y) {
        Color color1, color2;
        color1 = getColor(x, y - 1);
        color2 = getColor(x, y + 1);
        return gradient(color1, color2);
    }
    /**
     * initializes edges and weights.
     */
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
    /**
     * converts co-ordinate to a vertex in a graph.
     *
     * @param      col   The col
     * @param      row   The row
     *
     * @return     { description_of_the_return_value }
     */
    private int coordinateToVertex(int col, int row) {
        return (row * width()) + col;
    }
    /**
     * relaxes the edges.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    private void relax(int v, int w) {
        if (distTo[w] > distTo[v] + weights[w]) {
            distTo[w] = distTo[v] + weights[w];
            edgeTo[w] = v;
        }
    }
    /**
     * Determines if valid coordinate.
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     *
     * @return     True if valid coordinate, False otherwise.
     */
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width() && y >= 0 && y < height();
    }
    /**
     * adjacency matrix for a given graph.
     *
     * @param      col   The col
     * @param      row   The row
     *
     * @return     { description_of_the_return_value }
     */
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
    /**
     * relaxes the edges.
     */
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
    /**
     * computes the shortest path to the last vertx.
     *
     * @return     { description_of_the_return_value }
     */
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
    /**
     * computes shortest path.
     *
     * @param      lastVertex  The last vertex
     *
     * @return     { description_of_the_return_value }
     */
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
    /**
     * transposes the matrix.
     */
    private void transpose() {
        Picture tPicture = new Picture(height(), width());
        for (int row = 0; row < tPicture.height(); row++)
            for (int col = 0; col < tPicture.width(); col++)
                tPicture.set(col, row, getColor(row, col));

        picture = tPicture;
        transposed = !transposed;
    }
    /**
     * finds a seam.
     *
     * @return     { description_of_the_return_value }
     */
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
}