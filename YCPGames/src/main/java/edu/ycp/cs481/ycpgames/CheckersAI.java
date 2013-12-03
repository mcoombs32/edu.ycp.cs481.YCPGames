package edu.ycp.cs481.ycpgames;

import android.util.Log;

/**
 * Created by Brian on 11/25/13.
 */
public class CheckersAI extends CheckersPlayer {
	public CheckersAI(CheckersVal player, CheckersBoard board) {
		super(player);
		setHumanPlayer(false);
	}

	@Override
	public void findMove(Boolean isJump){

	}
	public int[] getSelected(){

		return null;
	}
	public int[] getMove(){

		return null;
	}
}
