package edu.ycp.cs481.ycpgames;

import android.util.Log;

/**
 * Created by brian on 10/28/13.
 */
public class DotsGame extends Game {
	String TAG = "YCPGamesDotsGame";
	//override Board board
	DotsBoard board;
	public DotsGame(){
		settings = Settings.getInstance();
		playerOne = new DotsPlayer(1);

		if (settings.isSinglePlayer()) {
			playerTwo = new DotsAI(2);
		} else {
			playerTwo = new DotsPlayer(2);
		}

		board = new DotsBoard();
	}
	/**
	 * So! this is how this is going to work. This is all placeholder stuff till I get the rest of
	 * the backend up and running. Pass me a x/y value of a node (one of the boxes) and a direction
	 * (is the line the top line or bottom line or left or right?).
	 *
	 * @return game state. -3 for invalid AI move, -2 if invalid move,
	 * 0 for game in progress, -1 for draw, otherwise player number of winner
	 */
	public int makeMove(int nodeX, int nodeY, Direction D){
		if(board.getLineAt(nodeX,nodeY,D) == BoardVal.EMPTY){
			//Draw Line
			board.drawLine(nodeX,nodeY,D,whosTurn());
			endTurn();
			if((settings.isSinglePlayer()) && (board.isGameOver() == BoardVal.IN_PROGRESS)){
				//call AI if single-player game and game is still in progress
				int[] location = playerTwo.makeMove(board.getGrid());

				Log.d(TAG, "AI move = "+ location[0]+" "+location[1]);
				//ai needs to make move, and I need to make sure its valid
			}
		}else{
			//space was taken
			return -2;
		}
		return 0;
	}

	/**
	 *Reset method
	 */
	@Override
	public void reset() {
		board.reset();
		playerOne.setMyTurn(true);


		if (settings.isSinglePlayer() && !playerTwo.isHumanPlayer()) {
			//if its single player and already has an AI player
			playerTwo.setMyTurn(false);
		} else if (settings.isSinglePlayer()) {
			//if its single player but not ai player
			playerTwo = new DotsAI(2);
		} else if (!settings.isSinglePlayer() && playerTwo.isHumanPlayer()) {
			//if its multiplayer and alraedy set with human player
			playerTwo.setMyTurn(false);
		} else {
			//if its multiplayer but set up with AI
			playerTwo = new DotsPlayer(2);
		}
	}

	/**
	 * @return an int with the player number of whos turn it is
	 */
	public BoardVal whosTurn() {
		if (playerOne.isPlayersTurn()) {
			return BoardVal.PLAYER_ONE;
		} else {
			return BoardVal.PLAYER_TWO;
		}
	}

	/**
	 * Switches who's turn it is
	 */
	private void endTurn() {
		playerOne.setMyTurn(!playerOne.isPlayersTurn());
		playerTwo.setMyTurn(!playerTwo.isPlayersTurn());
	}
}
