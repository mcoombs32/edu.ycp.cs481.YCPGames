package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/28/13.
 */
public class DotsGame extends Game {
	String TAG = "YCPGamesDotsGame";
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
	public int whosTurn() {
		if (playerOne.isPlayersTurn()) {
			return 1;
		} else {
			return 2;
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
