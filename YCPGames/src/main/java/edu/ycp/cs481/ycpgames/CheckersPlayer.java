package edu.ycp.cs481.ycpgames;

import android.util.Log;

/**
 * Created by brian on 11/24/13.
 */
public class CheckersPlayer {
	private static final String TAG = "YCPGamesCheckersPlayer";
	private CheckersVal playerNum;
	private boolean myTurn, humanPlayer = true;
	Settings settings;

	public CheckersPlayer(CheckersVal player) {
		playerNum = player;
		if (playerNum == CheckersVal.PLAYER_ONE) {
			myTurn = true;
		} else {
			myTurn = false;
		}
		settings = Settings.getInstance();
		Log.d(TAG, "Player " + " created");
	}

	public boolean isHumanPlayer() {
		return humanPlayer;
	}

	public void setHumanPlayer(boolean humanPlayer) {
		this.humanPlayer = humanPlayer;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(Boolean turn){
		myTurn = turn;
	}

	public CheckersVal getPlayerNum() {
		return playerNum;
	}
	public void findMove(Boolean isJump){
		Log.d(TAG,"findMove not implemented here");
	}
	public int[] getSelected(){
		Log.d(TAG,"getSelected not implemented here");
		return null;
	}
	public int[] getMove(){
		Log.d(TAG,"getMove not implemented here");
		return null;
	}

}
