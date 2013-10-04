package edu.ycp.cs481.ycpgames;

/**
 * Created by waffles on 10/4/13.
 */
public class TicTacToeBoard extends Board {
    //playerOne/TwoPieces will be used to count how many pieces each player has placed
    //this will help make isGameOver() faster in the early game
    int playerOnePieces, playerTwoPieces;
    public TicTacToeBoard(){
        /*
         * im going to standardize the board layout here
         * format of coordinates is (x,y)
         *      #     #
         * (0,2)#(1,2)#(2,2)
         * #################
         * (0,1)#(1,1)#(2,1)
         * #################
         * (0,0)#(1,0)#(2,0)
         *      #     #
         */

        for(int x = 0; x<3; x++){
            for(int y = 0; y<3; y++){
                grid[x][y] = 0;
            }
        }
        playerOnePieces = 0;
        playerTwoPieces = 0;
        return;
    }
    /*
     * overrides the placePiece method, takes a location and an int representing the player
     *
     */
    @Override
    public void placePiece(int x, int y, int player){
        grid[x][y] = player;
        if(player == 1){
            playerOnePieces++;
        }else{
            playerTwoPieces++;
        }
        return;
    }
    /*
     * overrides the checkSpace method, takes a location and returns true if it is a valid move
     */
    @Override
    public boolean checkSpace(int x, int y){
        if (grid[x][y] == 0){
            return true;
        }else{
            return false;
        }
    }
    /*
     * overrides the isGameOver method, recursively checks game board for victory
     * might be better to move these from board to game
     * i put them here since this was where grid was but now im not so sure
     * going to need to think about this for a few days to find the best way to do this
     */
    @Override
    public boolean isGameOver(){
        if((playerOnePieces<3)&&(playerTwoPieces<3)){
            return false;
        }
        //check this recursively
        return false;
    }
    @Override
    public boolean playerOneWin(){
        return true;
    }
}
