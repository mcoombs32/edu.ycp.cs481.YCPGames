package edu.ycp.cs481.ycpgames;

/**
 * Created by michaelcoombs on 11/4/13.
 */
public class DotsGrid {
    private static final int LENGTH = 1;
    private static final int WIDTH = 0;
    private int[] gridSize = Settings.getInstance().getDotsGridSize();
    private DotsGridCell[][] gridNodes;
    private int cellLength, cellWidth;


    public DotsGrid(int[] screenSize){
        cellWidth = screenSize[WIDTH]/gridSize[WIDTH];
        cellLength = screenSize[LENGTH]/(gridSize[LENGTH]+1);

        gridNodes = new DotsGridCell[gridSize[WIDTH]][gridSize[LENGTH]];
        for(int i = 0; i < gridSize[WIDTH];i++){
            for (int j = 0; j < gridSize[LENGTH]; j++) {
                gridNodes[i][j] = new DotsGridCell(cellLength, cellWidth, cellWidth * i, cellLength * j,i,j);
            }
        }

    }

    public int getGridWidth(){
        return gridSize[WIDTH];
    }

    public int getGridLength(){
        return gridSize[LENGTH];
    }
    public DotsGridCell getCell(int x, int y){
        return gridNodes[x][y];
    }



}
