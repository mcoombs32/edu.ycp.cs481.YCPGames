package edu.ycp.cs481.ycpgames;

//import android.util.Log;

/**
 * Created by brian on 10/28/13.
 */
public class DotsBoard extends Board {
	/*
	 * Origin (0,0)is set at Top left
	 */
	DotsNode [][] grid;
    private static int playerOneBoxes;
    private static int playerTwoBoxes;

    public DotsBoard(){
		reset();
	}

    public int getPlayerOneBoxes() {
        return playerOneBoxes;
    }

    public int getPlayerTwoBoxes() {
        return playerTwoBoxes;
    }

    public void reset(){
		DotsLine sharedLine;
		grid = new DotsNode[settings.getGridSize()-1][settings.getGridSize()-1];
		for(int x = 0; x < settings.getGridSize()-1; x++){
			for(int y = 0; y < settings.getGridSize()-1; y++){
				//initialize a grid of DotsNodes
				grid[x][y] = new DotsNode();
			}
		}
		for(int x = 0; x < settings.getGridSize()-1; x++){
			for(int y = 0; y < settings.getGridSize()-1; y++){
				sharedLine = new DotsLine();
                grid[x][y].setRight(sharedLine);
                if (x != settings.getGridSize()-2){
                    grid[x+1][y].setLeft(sharedLine);
                }

                sharedLine = new DotsLine();
                grid[x][y].setDown(sharedLine);
                if (y != settings.getGridSize()-2){
                    grid[x][y+1].setUp(sharedLine);
                }


			}
		}
        playerOneBoxes = 0;
        playerTwoBoxes = 0;
	}

	public void drawLine(int x, int y, Direction d, GameVal v){
        grid[x][y].setVal(d,v);
	}

	public GameVal getLineAt(int x, int y, Direction d){
		if((x <0) || (x>grid.length) || (y<0) || (y>grid[0].length)){
			return GameVal.ERROR;
		}
		return grid[x][y].getVal(d);
	}
    public void playerClaimUpdate(int x, int y,GameVal v){
        grid[x][y].checkForPlayerClaim(v);
    }

	public GameVal isGameOver(){
		playerOneBoxes = 0;
		playerTwoBoxes = 0;
		GameVal tempVal, checkVal = null;
		for(int x = 0; x < settings.getGridSize()-1; x++){
			for(int y = 0; y < settings.getGridSize()-1; y++){
                //grid[x][y].checkForPlayerClaim();
				tempVal = grid[x][y].isNodeFilled();
				if(tempVal == GameVal.EMPTY){
					checkVal = GameVal.IN_PROGRESS;
				}
				if(tempVal == GameVal.PLAYER_ONE){
					playerOneBoxes++;
				}
				if(tempVal == GameVal.PLAYER_TWO){
					playerTwoBoxes++;
				}
			}
		}
        if (checkVal == GameVal.IN_PROGRESS){
            return checkVal;
        }
		if(playerOneBoxes > playerTwoBoxes){
			return GameVal.PLAYER_ONE;
		}
		if(playerOneBoxes < playerTwoBoxes){
			return GameVal.PLAYER_TWO;
		}
		if(playerOneBoxes == playerTwoBoxes){
			return GameVal.DRAW;
		}
		return GameVal.IN_PROGRESS;
	}
	/**
	 *
	 * @return grid height
	 */
	@Override
	public int getGridHeight(){
		return grid.length;
	}

	/**
	 *
	 * @return grid width
	 */
	@Override
	public int getGridWidth(){
		return grid[0].length;
	}
}
