package edu.ycp.cs481.ycpgames;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 11/25/13.
 */
public class CheckersAI extends CheckersPlayer {
	private int [] selectedPiece;
	private int [] move;
	private CheckersBoard board;
	public CheckersAI(CheckersVal player, CheckersBoard b) {
		super(player);
		board = b;
		setHumanPlayer(false);
		selectedPiece = null;
		move = null;
	}


	@Override
	public void findMove(Boolean isJump){

	}
	@Override
	public int[] getSelectedPiece(){
		return selectedPiece;
	}
	@Override
	public int[] getMove(){
		return move;
	}
	@Override
	public void setMyTurn(Boolean turn){
		super.setMyTurn(turn);
		selectedPiece = null;
		move = null;
	}

	//helper methods

	private int[] minimax(int depth, CheckersVal player){
		//find all pieces
		List<int[]> pieces = findPieces();
		//set goal
		int bestScore;
		if(super.getPlayerNum() == player){
			bestScore = Integer.MIN_VALUE;
		}else{
			bestScore= Integer.MAX_VALUE;
		}
		int currentScore;

		return null;
	}

	/**
	 *
	 * @param x x value of space to check
	 * @param y y value of space to check
	 * @return boolean indicating if move is valid
	 */
	private boolean isMoveValid(int x, int y){
		if(board.getPieceAt(x,y).getPlayer() == CheckersVal.EMPTY){
			return true;
		}
		return false;
	}

	/**
	 *
	 * @return a list of all of AIs pieces
	 */
	private List<int[]> findPieces(){
		List<int[]> moves = new ArrayList<int[]>();

		for (int x = 0; x <= 8; x++) {
			for (int y = 0; y <= 8; y++) {
				if(board.getPieceAt(x,y).getPlayer() == super.getPlayerNum()){
					moves.add(new int[]{x, y});
				}
			}
		}

		return moves;
	}

	/**
	 * evaluate the board
	 * score += (AIpieces - OppPieces)^3
	 * score += 10*AIkings
	 * score += 20*AIjumps
	 * score -= 10*OppKings
	 * score -= 20*OppJumps
	 * @return score
	 */
	private int evaluate(){
		int score = (int)Math.pow(board.getPlayerTwoPieces()-board.getPlayerOnePieces(),3);
		List<int[]> moves;
		CheckersPiece temp;
		for (int x = 0; x <= 8; x++) {
			for (int y = 0; y <= 8; y++) {
				temp = board.getPieceAt(x,y);
				if(temp.getRank() == CheckersVal.KING){
					if(temp.getPlayer() == super.getPlayerNum()){
						//if piece is your king +10 points
						score += 10;
					}else{
						//if piece is enemy king -10 points
						score -= 10;
					}

				}
				//evaluate moves
				moves = board.getValidMoves(x,y);
				for(int [] move : moves){
					if(move[2] == 1){
						//if move is a jump
						if(temp.getPlayer() == super.getPlayerNum()){
							score += 20;
						}else{
							score -=20;
						}
					}
				}
			}
		}
		return score;
	}
}
