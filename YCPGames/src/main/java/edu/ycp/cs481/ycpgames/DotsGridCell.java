package edu.ycp.cs481.ycpgames;

/**
 * Created by michaelcoombs on 11/4/13.
 */
public class DotsGridCell {

    private int length,width,x,x1,y,y1;

    public int getArrX() {
        return arrX;
    }

    public void setArrX(int arrX) {
        this.arrX = arrX;
    }

    public int getArrY() {
        return arrY;
    }

    public void setArrY(int arrY) {
        this.arrY = arrY;
    }

    private int arrX, arrY;
    private int[] center;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getX1() {
        return x1;
    }

    public int getY() {
        return y;
    }

    public int getY1() {
        return y1;
    }

    public int[] getCenter() {
        return center;
    }

    public DotsGridCell(int length, int width, int x, int y, int arrX, int arrY){
        this.length = length;
        this.width = width;
        this.x = x;
        this.y = y;
        x1 = x + this.width;
        y1 = y + this.length;
        this.arrX = arrX;
        this.arrY = arrY;

        center = new int[2];
        center[0] = x1 - (width/2);
        center[1] = y1 - (length/2);

    }

}
