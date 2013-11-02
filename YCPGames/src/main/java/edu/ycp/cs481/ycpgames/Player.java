package edu.ycp.cs481.ycpgames;

import android.util.Log;

/**
 * Created by brian on 10/4/13.
 */
public class Player {
	private static final String TAG = "YCPGamesPlayer";
	public int playerNum;
	private boolean myTurn;
	private boolean humanPlayer = true;
	Settings settings;

	/**
	 * constructor
	 *
	 * @param num player number
	 */
	public Player(int num) {
		playerNum = num;
		if (playerNum == 1) {
			myTurn = true;
		} else {
			myTurn = false;
		}
		settings = Settings.getInstance();
		Log.d(TAG, "Player " + " created");
	}

	/**
	 * @return boolean indicating if it is that players turn
	 */
	public boolean isPlayersTurn() {
		return myTurn;
	}

	/**
	 * @param turn indicates whether it is players turn
	 */
	public void setMyTurn(boolean turn) {
		myTurn = turn;
	}

	/**
	 * @return boolean indicating whether this is a human player
	 */
	public boolean isHumanPlayer() {
		return humanPlayer;
	}

	/**
	 * @param human boolean used to set if it is a human player
	 */
	public void setHumanPlayer(boolean human) {
		humanPlayer = human;
	}


	public int[] makeMove(int[][] g) {
		Log.d(TAG, "Make move is not implemented here");
		return null;
	}
}
