package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/4/13.
 */
public class TicTacToeGame extends Game{
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
}
