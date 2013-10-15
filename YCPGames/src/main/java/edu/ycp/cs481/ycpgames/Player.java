package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/4/13.
 */
public class Player {
    public int playerNum;
    private boolean myTurn;
    Settings settings;
    public Player(int num){
        playerNum = num;
        if(playerNum == 1){
            myTurn = true;
        }else{
            myTurn = false;
        }
        settings = Settings.getInstance();
    }
    public boolean isPlayersTurn(){
        return myTurn;
    }

}
