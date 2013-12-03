package edu.ycp.cs481.ycpgames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/18/13.
 */
public class CheckersGame {
	private static final String TAG = "YCPGamesCheckersGame";
	Settings settings = Settings.getInstance();
	private List<int[]> validMoves;
	private int selectedX, selectedY;
	private CheckersPlayer playerOne;
	private CheckersPlayer playerTwo;
	private CheckersBoard board = new CheckersBoard();
	private boolean isJumpMode;
	public CheckersGame(){
		reset();
	}

	/**
	 * resets everything
	 */
	public void reset() {
		validMoves = new ArrayList<int[]>();
		selectedX = -1;
		selectedY = -1;
		board.reset();
		isJumpMode = false;
		playerOne = new CheckersPlayer(CheckersVal.PLAYER_ONE);
		if(settings.isSinglePlayer()){
			playerTwo = new CheckersAI(CheckersVal.PLAYER_TWO, board);
		}else{
			playerTwo = new CheckersPlayer(CheckersVal.PLAYER_TWO);
		}
	}

	/**
	 * used to select a piece, fills valid moves array
	 *
	 * @param x x value of piece to select
	 * @param y y value of piece to select
	 * @return true if valid piece to select false otherwise
	 */
	public boolean selectPiece(int x, int y){
		if((board.getPieceAt(x,y).getPlayer() == whosTurn()) && !isJumpMode){
			selectedX = x;
			selectedY = y;
			validMoves = board.getValidMoves(x,y);
			return true;
		}else{
			return false;
		}
	}

	/**
	 *
	 * @return an array of valid moves for selected piece
	 */
	public List<int[]> getValidMoves() {
		return validMoves;
	}

	/**
	 *
	 * @param x    x value of move
	 * @param y    y value of move
	 * @return    game state
	 * 		0 for game in progresss, 1 for player 1 win, 2 for player 2 win
	 * 		-1 for error
	 */
	public int makeMove(int x, int y){
		boolean jumpAvailible = false;
		int[] selectedMove = null;
		for(int[] move : validMoves){
			if((move[0] == x) && (move[1] == y)){
				selectedMove = move;
				break;
			}
		}
		//return if invalid move
		if (selectedMove == null){
			return -1;
		}
		board.makeMove(selectedX,selectedY,selectedMove);
		if(board.isGameOver() != 0){
			return board.isGameOver();
		}
		if(selectedMove[2] == 0){
			//if move was not a jump then end turn
			endTurn();
		}else{
			//if move was a jump check to see if there is another jump availible
			validMoves = board.getValidMoves(selectedX,selectedY);
			for(int[] move : validMoves){
				if(move[2] == 1){
					//a jump was found
					jumpAvailible = true;
				}
			}
			if(jumpAvailible){
				//if a jump is found go to jump mode
				selectedX = selectedMove[0];
				selectedY = selectedMove[1];
				isJumpMode = true;
				validMoves = board.getValidMoves(selectedX,selectedY);
			}else{
				//if there is not another jump then end turn
				endTurn();
			}
		}

		//if AI move
		if(settings.isSinglePlayer() && (whosTurn() == CheckersVal.PLAYER_TWO)){
			do{
				//find AI move
				playerTwo.findMove(isJumpMode);
				selectedX = playerTwo.getSelectedPiece()[0];
				selectedY = playerTwo.getSelectedPiece()[1];
				selectedMove = playerTwo.getMove();

				if(isJumpMode){
					//if no jump availilble return
					jumpAvailible = false;
					for(int[] move : validMoves){
						if(move[2] == 1){
							//a jump was found
							jumpAvailible = true;
						}
					}
					if(!jumpAvailible){
						endTurn();
						return board.isGameOver();
					}
				}
				//make move
				board.makeMove(selectedX,selectedY,selectedMove);
				if(board.isGameOver() != 0){
					return board.isGameOver();
				}
			}while(isJumpMode);
			//end turn
			endTurn();
		}
		return board.isGameOver();
	}

	/**
	 *
	 * @param x    x location of piece to get
	 * @param y    y location of piece to get
	 * @return piece at board[x][y]
	 */
	public CheckersPiece getCheckersPieceAt(int x, int y){
		return board.getPieceAt(x,y);
	}
	/**
	 *
	 * @return CheckersVal of whos turn it is
	 */
	public CheckersVal whosTurn(){
		if(playerOne.isMyTurn()){
			return CheckersVal.PLAYER_ONE;
		}else{
			return CheckersVal.PLAYER_TWO;
		}
	}
	/**
	 * Switches who's turn it is
	 */
	private void endTurn() {
		playerOne.setMyTurn(!playerOne.isMyTurn());
		playerTwo.setMyTurn(!playerTwo.isMyTurn());
		selectedX = -1;
		selectedY = -1;
		isJumpMode = false;
		validMoves = new ArrayList<int[]>();
	}

}
