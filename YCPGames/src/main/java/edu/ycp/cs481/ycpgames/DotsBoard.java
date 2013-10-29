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
		for(int x = 0; x < settings.getGirdSize()-1; x++){
			for(int y = 0; y < settings.getGirdSize()-1; y++){
				//use alliasing to to link the overlaping values
				//TODO: needs some JUNITS, im not 100% sure this will work how i want it too
				grid[x+1][y].left = grid[x][y].right;
				grid[x][y+1].down = grid[x][y].up;
			}
		}
	}

	public void drawLine(int x, int y, Direction d, BoardVal v){
		grid[x][y].setVal(d,v);
	}
	public BoardVal getLineAt(int x, int y, Direction d){
		return grid[x][y].getVal(d);
	}
	public DotsNode[][] getDotsGrid(){
		return grid;
	}
	public BoardVal isGameOver(){
		int playerOneBoxes = 0;
		int playerTwoBoxes = 0;
		BoardVal tempVal;
		for(int x = 0; x < settings.getGirdSize(); x++){
			for(int y = 0; y < settings.getGirdSize(); y++){
				tempVal = grid[x][y].isNodeFilled();
				if(tempVal == BoardVal.EMPTY){
					return BoardVal.EMPTY;
				}
				if(tempVal == BoardVal.PLAYER_ONE){
					playerOneBoxes++;
				}
				if(tempVal == BoardVal.PLAYER_TWO){
					playerTwoBoxes++;
				}
			}
		}
		if(playerOneBoxes > playerTwoBoxes){
			return BoardVal.PLAYER_ONE;
		}
		if(playerOneBoxes < playerTwoBoxes){
			return BoardVal.PLAYER_TWO;
		}
		if(playerOneBoxes == playerTwoBoxes){
			return BoardVal.DRAW;
		}
		return null;
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
