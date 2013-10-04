package edu.ycp.cs481.ycpgames;

/**
 * Created by waffles on 10/4/13.
 */
public class Game {
    Player playerOne;
    Player playerTwo;
    Board board= new Board();
    public Game(){
        playerOne = new Player();
        playerTwo = new Player();
        board = new Board();
    }

}
