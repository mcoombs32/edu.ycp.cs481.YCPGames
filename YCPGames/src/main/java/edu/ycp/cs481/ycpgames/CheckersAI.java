package edu.ycp.cs481.ycpgames;

/**
 * Created by Brian on 11/25/13.
 */
public class CheckersAI extends CheckersPlayer {
	public CheckersAI(CheckersVal player) {
		super(player);
		setHumanPlayer(false);
	}

	@Override
	public int makeMove(){
		return 0;
	}
}
