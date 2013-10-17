package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/4/13.
 */
public class TicTacToeGame extends Game{
	private static final String TAG = "YCPGamesTicTacToeGame";
    public TicTacToeGame(){
        settings = Settings.getInstance();
        playerOne = new TicTacToePlayer(1);

        if(settings.isSinglePlayer()){
            playerTwo = new TicTacToeAI(2);
        }else{
            playerTwo = new TicTacToePlayer(2);
        }

        board = new Board();
    }
	/*
	 *Reset method
	 */
	@Override
	public void reset(){
		board.reset();
		playerOne.setMyTurn(true);


		if(settings.isSinglePlayer() && !playerTwo.isHumanPlayer()){
			//if its single player and already has an AI player
			playerTwo.setMyTurn(false);
		}else if(settings.isSinglePlayer()){
			//if its single player but not ai player
			playerTwo = new TicTacToeAI(2);
		}else if(!settings.isSinglePlayer() && playerTwo.isHumanPlayer()){
			//if its multiplayer and alraedy set with human player
			playerTwo.setMyTurn(false);
		}else{
			//if its multiplayer but set up with AI
			playerTwo = new TicTacToePlayer(2);
		}
	}
	/**
	 * @return an int with the player number of whos turn it is
	 */
	public int whosTurn(){
		if(playerOne.isPlayersTurn()){
			return 1;
		}else{
			return 2;
		}
	}
	/**
	 * @param x  x location of move
	 * @param y  y location of move
	 * this method figure out which players turn it is and performs a move
	 * if it is a single player game it also calls the AI
	 * @return game state. -2 if invalid move. 0 for game in progress, -1 for draw, otherwise palyer number of winner
	 */
	public int move(int x, int y){
		if(board.checkSpace(x,y)){
			//if is valid move then place piece
			board.placePiece(x,y,whosTurn());
			endTurn();
			if(settings.isSinglePlayer() && (board.isGameOver() == 0)){
				//call AI if singleplayer game and game is still in progress
				int[] location = playerTwo.makeMove(board.getGrid());
				if((location[0] == -1) || (location[1] == -1)){
					//TODO: AI made invalid move, crash
				}else if(board.checkSpace(location[0], location[1])){
					//if location checks out place piece and end turn
					board.placePiece(location[0], location[1], whosTurn());
					endTurn();
				}else{
					//TODO: AI made invalid move, crash
				}
			}
		}else{
			//else return false
			return -2;
		}
		//return game state
		return board.isGameOver();
	}
	/*
	 * Switches whos turn it is
	 */
	private void endTurn(){
		playerOne.setMyTurn(!playerOne.isPlayersTurn());
		playerTwo.setMyTurn(!playerTwo.isPlayersTurn());
	}
}
