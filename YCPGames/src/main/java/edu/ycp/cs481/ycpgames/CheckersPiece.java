package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 11/24/13.
 */
public class CheckersPiece {
	private CheckersVal player;
	private CheckersVal rank;
	public CheckersPiece(CheckersVal player){
		this.player = player;
		rank = CheckersVal.PAWN;
	}

	public CheckersVal getPlayer() {
		return player;
	}

	public CheckersVal getRank() {
		return rank;
	}

	public void promote(){
		rank = CheckersVal.KING;
	}

	public void kill(){
		player = CheckersVal.EMPTY;
		rank = CheckersVal.EMPTY;
	}
}
