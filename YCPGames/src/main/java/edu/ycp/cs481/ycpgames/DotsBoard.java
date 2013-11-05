package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/28/13.
 */
public class DotsBoard extends Board {
	/*
	 * Origin (0,0)is set at bottom left
	 */
	DotsNode [][] grid;
	public DotsBoard(){
		reset();
	}

	public void reset(){
		grid = new DotsNode[settings.getGirdSize()][settings.getGirdSize()];
		for(int x = 0; x < settings.getGirdSize(); x++){
			for(int y = 0; y < settings.getGirdSize(); y++){
				grid[x][y] = new DotsNode();
			}
		}
		for(int x = 0; x < settings.getGirdSize()-1; x++){
			for(int y = 0; y < settings.getGirdSize()-1; y++){
				//use alliasing to to link the overlaping values
				//TODO: needs some JUNITS, im not 100% sure this will work how i want it too
				grid[x+1][y].left = grid[x][y].right;
				grid[x][y+1].down = grid[x][y].up;
			}
		}
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

	public DotsNode[][] getDotsGrid(){
		return grid;
	}
	public GameVal isGameOver(){
		int playerOneBoxes = 0;
		int playerTwoBoxes = 0;
		GameVal tempVal;
		for(int x = 0; x < settings.getGirdSize(); x++){
			for(int y = 0; y < settings.getGirdSize(); y++){
				tempVal = grid[x][y].isNodeFilled();
				if(tempVal == GameVal.EMPTY){
					return GameVal.EMPTY;
				}
				if(tempVal == GameVal.PLAYER_ONE){
					playerOneBoxes++;
				}
				if(tempVal == GameVal.PLAYER_TWO){
					playerTwoBoxes++;
				}
			}
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
