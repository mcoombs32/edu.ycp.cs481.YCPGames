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
     * returns 0 for game still in progress, otherwise player number of victor
     */
    @Override
    public int isGameOver(){
        int player;
        if((playerOnePieces<3)&&(playerTwoPieces<3)){
            return 0;
        }
        //look for verticle victories
        for(int x = 0; x < 3; x++){
            player = recurse("up", x, 0);
            if (player != 0){
                return player;
            }
        }
        //look for horizontal victories
        for(int y = 0; y <3; y++){
            player = recurse("right", y, 0);
            if (player != 0){
                return player;
            }
        }
        //look for diagonal victories
        player = recurse("dUP",0,0);
        if(player != 0){
            return player;
        }
        player = recurse("dDOWN",0,2);
        if(player != 0){
            return player;
        }
        //check this recursively
        return 0;
    }
    @Override
    public boolean playerOneWin(){
        return true;
    }

    private int recurse(String flag, int x, int y){
        int player;
        if(flag == "up"){
            if(y == 2){
                return grid[x][y];
            }else{
                player = recurse(flag, x, y+1);
                if(player == grid[x][y]){
                    return player;
                }else{
                    return 0;
                }
            }
        }
        if(flag == "right"){
            if (x==2){
                return grid[x][y];
            }else{
                player = recurse(flag, x+1, y);
                if(player == grid[x][y]){
                    return player;
                }else{
                    return 0;
                }
            }
        }
        if(flag == "dUP"){
            if (x==2){
                return grid[x][y];
            }else{
                player = recurse(flag, x+1, y+1);
                if(player == grid[x][y]){
                    return player;
                }else{
                    return 0;
                }
            }
        }if (flag== "dDOWN"){
            if (x==2){
                return grid[x][y];
            }else{
                player = recurse(flag, x+1,y-1);
                if(player == grid[x][y]){
                    return player;
                }else{
                    return 0;
                }
            }
        }
        return 0;
    }
}
