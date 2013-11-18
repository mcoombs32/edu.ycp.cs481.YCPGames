package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 11/18/13.
 */
public class CheckersGame extends Game{
	private int[][] validMoves;
	private int selectedX, selectedY;
	public CheckersGame(){
		super();
	}

	@Override
	public void reset() {
		super.reset();
	}

	public int[][] selectPiece(int x, int y){
		if(/*TODO:isValidPice*/true){
			selectedX = x;
			selectedY = y;
		}
		return null/*TODO:validMoves*/;
	}
	public int makeMove(int x, int y){
		return 0;
	}
}
