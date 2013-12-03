package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/28/13.
 */

import android.util.Log;

public class DotsGame extends Game {
	String TAG = "YCPGamesDotsGame";
	//override Board board
	DotsBoard board;
	public DotsGame(){
		settings = Settings.getInstance();
		playerOne = new DotsPlayer(1);

		if (settings.isSinglePlayer()) {
            playerTwo = new DotsAI(2);
            Log.d(TAG, "AI Created");
		} else {
			playerTwo = new DotsPlayer(2);
            Log.d(TAG, "Not AI Created");
		}

		board = new DotsBoard();
	}

	/**
	 *
	 * @return number of boxes player one has made
	 */
	public int getPlayerOneScore(){
		return board.getPlayerOneBoxes();
	}

	/**
	 *
	 * @return number of boxes player two has made
	 */
	public int getPlayerTwoScore(){
		return board.getPlayerTwoBoxes();
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
		if(board.getLineAt(nodeX,nodeY,D) == GameVal.EMPTY){
			//Draw Line if game is in progress
			if(board.isGameOver() == GameVal.IN_PROGRESS){
				board.drawLine(nodeX,nodeY,D,whosTurn());
                for (int i = 0; i < board.getGridHeight(); i++){
                    for (int j = 0; j < board.getGridWidth(); j++){
                        board.playerClaimUpdate(whosTurn());
                    }
                }
				endTurn();
			}
			if((Settings.getInstance().isSinglePlayer()) && (board.isGameOver() == GameVal.IN_PROGRESS)&& (playerTwo.isPlayersTurn())){
				//call AI if single-player game and game is still in progress
				Log.d(TAG,"calling ai");
				int[] location = playerTwo.makeDotsMove(board.getDotsGrid());
				Log.d(TAG,"AI move. x="+location[0] + " y="+location[1]+" direction="+location[2]);
				//make sure its a valid move

				/*logic for placing piece
				 *need to convert from int into GameVal
				 *
				 * move[0] x position of move
				 * move[1] y position of move
				 * move[2] direction of move
				 * 			0 = up
				 * 			1 = down
				 * 			2 = left
				 * 			3 = right
				 * this isn't the most elegant method but it works
				 */
				switch (location[2]){
					case 0:
						if(board.getLineAt(location[0],location[1],Direction.UP)== GameVal.ERROR){
							return -3;
						}
						board.drawLine(location[0],location[1],Direction.UP,whosTurn());
						break;
					case 1:
						if(board.getLineAt(location[0],location[1],Direction.DOWN)== GameVal.ERROR){
							return -3;
						}
						board.drawLine(location[0],location[1],Direction.DOWN,whosTurn());
						break;
					case 2:
						if(board.getLineAt(location[0],location[1],Direction.LEFT)== GameVal.ERROR){
							return -3;
						}
						board.drawLine(location[0],location[1],Direction.LEFT,whosTurn());
						break;
					case 3:
						if(board.getLineAt(location[0],location[1],Direction.RIGHT)== GameVal.ERROR){
							return -3;
						}
						board.drawLine(location[0],location[1],Direction.RIGHT,whosTurn());
						break;
					default:
						return -3;
				}
                board.playerClaimUpdate(whosTurn());
				endTurn();
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
			playerTwo = new DotsAI(2);
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
	 * @return a gameval indicating the player whos turn it is
	 */
	public GameVal whosTurn() {
		if (playerOne.isPlayersTurn()) {
			return GameVal.PLAYER_ONE;
		} else {
			return GameVal.PLAYER_TWO;
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
