import java.awt.Color;
public class SeamCarver {
    // create a seam carver object based on the given picture
    private int width;
    private int height;
    private Picture picture;
    private double[][] energyvals;
    public SeamCarver(Picture pic1) {
        width = pic1.width();
        height = pic1.height();
        picture = pic1;
        energyvals = new double[height][width];
        int x = 0;
        int y = 0;
        Color color1,color2;
        for (int i = 0; i< height;i++){
            for (int j = 0; j < width; j++){
                x = 0;
                y = 0;
                if (((i==0)||(j==0))||((i==(height-1))||(j==(width-1)))){
                    energyvals[i][j] = 1000;
                } else {
                    color1 = picture.get(j+1, i);
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
    public Picture picture() {
        return picture;
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
    public double energy(int x, int y) {
        return energyvals[y][x];
    }
    public int[] findHorizontalSeam() {
        return new int[0];
    }
    public int[] findVerticalSeam() {
        return new int[0];
    }
    public void removeHorizontalSeam(int[] seam) {

    }
    public void removeVerticalSeam(int[] seam) {

    }
}