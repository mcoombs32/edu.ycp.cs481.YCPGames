package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/4/13.
 */
public class Game {
	private static final String TAG = "YCPGamesGame";
    Player playerOne;
    Player playerTwo;
    Board board= new Board();
    Settings settings;
    public Game(){
        settings = Settings.getInstance();
        playerOne = new Player(1);
        playerTwo = new Player(2);
        board = new Board();
    }
	public void reset(){
		playerOne.setMyTurn(true);
		playerTwo.setMyTurn(false);
		board.reset();
	}
}
