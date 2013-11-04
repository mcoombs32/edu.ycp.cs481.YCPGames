package edu.ycp.cs481.ycpgames;

/**
 * Created by michaelcoombs on 11/4/13.
 */
public class DotsGridCell {

    private int length,width,x,x1,y,y1;
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

    public DotsGridCell(int length, int width, int x, int y){
        this.length = length;
        this.width = width;
        this.x = x;
        this.y = y;
        x1 = x + this.width;
        y1 = y + this.length;

        center = new int[2];
        center[0] = x1 - (width/2);
        center[1] = y1 - (length/2);

    }

}
