import java.awt.Color;
public class SeamCarver {
    private int width;
    private int height;
    private Picture pic;
    private double[][] energyvals;
    public SeamCarver(Picture pic1) {
        width = pic1.width();
        height = pic1.height();
        pic = pic1;
        energyvals = new double[width][height];
        int x = 0;
        int y = 0;
        Color c1,c2;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                x = 0;
                y = 0;
                if (((i==0)||(j==0))||((i==(height-1))||(j==(width-1)))) {
                    energyvals[i][j] = 1000;
                } else {
                    c1 = pic.get(j+1, i);
                    c2 = pic.get(j - 1, i);
                    x += Math.pow((c1.getRed() - c2.getRed()), 2);
                    x += Math.pow((c1.getGreen() - c2.getGreen()), 2);
                    x += Math.pow((c1.getBlue() - c2.getBlue()), 2);
                    c1 = pic.get(j, i +1);
                    c2 = pic.get(j, i - 1);
                    y += Math.pow((c1.getRed() - c2.getRed()), 2);
                    y += Math.pow((c1.getGreen() - c2.getGreen()), 2);
                    y += Math.pow((c1.getBlue() - c2.getBlue()), 2);
                    energyvals[i][j] = Math.sqrt(x + y);
                }
        }
    }
  }
  public Picture picture() {
    return pic;
  }
  public int width() {
    return width;
  }
  public int height() {
    return height;
  }
  public double energy(int x, int y) {
    return energyvals[x][y];
  }
  public int[] findHorizontalSeam() {
    return new int[0];
  }
  public int[] findVerticalSeam() {
    return new int[0];
  }
  public void removeHorizontalSeam(int[] seam){

  }
  public void removeVerticalSeam(int[] seam) {

  }
}