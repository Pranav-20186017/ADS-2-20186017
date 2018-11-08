import java.awt.Color;
/**
 * Class for seam carver.
 */
public class SeamCarver {
    // create a seam carver object based on the given picture
    private int width;
    private int height;
    private Picture picture;
    private double[][] energyvals;
    /**
     * Constructs the object.
     *
     * @param      pic1  The picture 1
     */
    public SeamCarver(final Picture pic1) {
        if (pic1 == null) {
            System.out.println("picture is null");
            return;
        }
        width = pic1.width();
        height = pic1.height();
        picture = pic1;
        energyvals = new double[height][width];
        int x = 0;
        int y = 0;
        Color color1, color2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                x = 0;
                y = 0;
                if (((i == 0) || (j == 0)) || ((i == (height - 1)) || (j == (width - 1)))) {
                    energyvals[i][j] = 1000;
                } else {
                    color1 = picture.get(j + 1, i);
                    color2 = picture.get(j - 1, i);
                    x += Math.pow((color1.getRed() - color2.getRed()), 2);
                    x += Math.pow((color1.getGreen() - color2.getGreen()), 2);
                    x += Math.pow((color1.getBlue() - color2.getBlue()), 2);
                    color1 = picture.get(j, i +1);
                    color2 = picture.get(j, i - 1);
                    y += Math.pow((color1.getRed() - color2.getRed()), 2);
                    y += Math.pow((color1.getGreen() - color2.getGreen()), 2);
                    y += Math.pow((color1.getBlue() - color2.getBlue()), 2);
                    energyvals[i][j] = Math.sqrt(x + y);
                }
            }
        }
    }
    /**
     * returns the picture object.
     *
     * @return     { description_of_the_return_value }
     */
    public Picture picture() {
        return picture;
    }
    /**
     * returns width of the picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int width() {
        return width;
    }
    /**
     * returns height of the picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int height() {
        return height;
    }
    /**
     * returns an array of energy values of pixels.
     *
     * @param      x     { parameter_description }
     * @param      y     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public double energy(final int x, final int y) {
        return energyvals[y][x];
    }
    /**
     * finds the horizontal seam in a given picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findHorizontalSeam() {
        return new int[0];
    }
    /**
     * finds the vertical seam in a given picture.
     *
     * @return     { description_of_the_return_value }
     */
    public int[] findVerticalSeam() {
        return new int[0];
    }
    /**
     * Removes a horizontal seam.
     *
     * @param      seam  The seam
     */
    public void removeHorizontalSeam(final int[] seam) {

    }
    /**
     * Removes a vertical seam.
     *
     * @param      seam  The seam
     */
    public void removeVerticalSeam(final int[] seam) {

    }
}