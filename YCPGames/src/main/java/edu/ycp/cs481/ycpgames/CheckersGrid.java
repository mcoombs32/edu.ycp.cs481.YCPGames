package edu.ycp.cs481.ycpgames;


/**
 * Created by michaelcoombs on 12/2/13.
 */
public class CheckersGrid {
    private static final int LENGTH = 1;
    private static final int WIDTH = 0;
    private int[] gridSize = {8,10};
    private CheckersGridCell[][] gridNodes;
    private int cellLength, cellWidth;


    public CheckersGrid(int[] screenSize){
        cellWidth = screenSize[WIDTH]/gridSize[WIDTH];
        cellLength = screenSize[LENGTH]/(gridSize[LENGTH]);

        gridNodes = new CheckersGridCell[gridSize[WIDTH]][gridSize[LENGTH]];
        for(int i = 0; i < gridSize[WIDTH];i++){
            for (int j = 0; j < gridSize[LENGTH]; j++) {
                if(j == 0||j ==gridSize[LENGTH]){
                    gridNodes[i][j] = null;
                }else{
                    gridNodes[i][j] = new CheckersGridCell(cellLength, cellWidth, cellWidth * i, cellLength * j,i,j);
                }
            }
        }

    }

    public int getGridWidth(){
        return gridSize[WIDTH];
    }

    public int getGridLength(){
        return gridSize[LENGTH];
    }
    public CheckersGridCell getCell(int x, int y){
        return gridNodes[x][y];
    }
}
