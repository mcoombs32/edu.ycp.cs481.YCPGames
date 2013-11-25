package edu.ycp.cs481.ycpgames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/18/13.
 */
public class CheckersGame {
	private List<int[]> validMoves = new ArrayList<int[]>();;
	private int selectedX = -1, selectedY = -1;
	private CheckersPlayer playerOne = new CheckersPlayer(CheckersVal.PLAYER_ONE);
	private CheckersPlayer playerTwo = new CheckersPlayer(CheckersVal.PLAYER_TWO);
	private CheckersBoard board = new CheckersBoard();
	public CheckersGame(){
		super();
	}

	/**
	 * resets everything
	 */
	public void reset() {
		playerOne.setMyTurn(true);
		playerTwo.setMyTurn(false);
		board.reset();
	}

	/**
	 * used to select a piece, fills valid moves array
	 *
	 * @param x x value of piece to select
	 * @param y y value of piece to select
	 * @return true if valid piece to select false otherwise
	 */
	public boolean selectPiece(int x, int y){
		if(board.getPieceAt(x,y) == whosTurn()){
			selectedX = x;
			selectedY = y;
			validMoves = board.getValidMoves(x,y);
			return true;
		}else{
			return false;
		}
	}

	/**
	 *
	 * @return an array of valid moves for selected piece
	 */
	public List<int[]> getValidMoves() {
		return validMoves;
	}

	/**
	 *
	 * @param move move values, needs to be an array returned by getValid moves, otherwise things break
	 * @return code
	 */
	public void makeMove(int[] move){

	}

	/**
	 *
	 * @return CheckersVal of whos turn it is
	 */
	public CheckersVal whosTurn(){
		if(playerOne.isMyTurn() == true){
			return CheckersVal.PLAYER_ONE;
		}else{
			return CheckersVal.PLAYER_TWO;
		}
	}
	/**
	 * Switches who's turn it is
	 */
	private void endTurn() {
		playerOne.setMyTurn(!playerOne.isMyTurn());
		playerTwo.setMyTurn(!playerTwo.isMyTurn());
		selectedX = -1;
		selectedY = -1;
		validMoves = new ArrayList<int[]>();;
	}
}
