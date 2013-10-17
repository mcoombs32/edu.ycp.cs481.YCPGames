package edu.ycp.cs481.ycpgames;

import java.util.Random;
import java.util.Set;

/**
 * Created by brian on 10/4/13.
 *
 * AI adapted from:
 * http://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
 */
public class TicTacToeAI extends Player {
	private static final String TAG = "YCPGamesTicTacToeAI";
    private int difficulty;
    Random rand;
    int[][] grid;
    /*
     * lookup table of prefered moves
     */
    private int[][] preferredMoves = {
            {1,1}, {0,2}, {0,0},
            {2,2}, {2,0}, {0,1},
            {1,2}, {1,0}, {2,1}};

    public TicTacToeAI(int num){
        super(num);
        difficulty = settings.getDifficulty();//get difficulty from settings
        rand  = new Random();
		setHumanPlayer(false);
    }
	@Override
    public int[] makeMove(int [][] g){
        int[] move = {0,0};
        grid = g;
        switch (difficulty){
            case 0://difficulty low (very very low)
                move = randMove();
                break;
            case 1://difficulty medium (prefers some moves over others, but will make no attempt to
                   // block player 1 win
                move = lookupTable();
                break;
            case 2://difficulty high
                //TODO: Implement this
                break;
            default://error
                move[0] = -1;
                move[1] = -1;
                break;
        }


        return move;
    }

    /*
     * Helper method to come up with a random move
     * used for lowest difficulty setting
     */
    private int[] randMove(){
        int[] move = {0,0};
        do{
            move[0] = rand.nextInt(3);
            move[1] = rand.nextInt(3);
        }while(!confirmValidMove(move));

        return move;
    }

    /*
     * helper method to ensure that a move is valid
     */
    private boolean confirmValidMove(int[] move){
        if(grid[move[0]][move[1]] == 0){
            return true;
        }
        return false;
    }

    private int[] lookupTable(){
        for(int[] move : preferredMoves){
            if (grid[move[0]][move[1]] == 0){
                return move;
            }
        }
        //no empty spaces
        return null;
    }


    /*
     *TODO: figure out what is going on and implement
     *
     */
    private int[]minimax(int depth, int player){

        return null;
    }
    /*
     * generate valid moves
     * TODO: implement
     */
    private int[][] generateMoves(){


        return null;
    }

    /*
     * heuristic eval function for the current board state
     * returns:
     * +100, +10, +1 for EACH 3-, 2-, 1-in-a-line for computer.
     * -100, -10, -1 for EACH 3-, 2-, 1-in-a-line for opponent.
     * 0 otherwise
     */
    private int evaluate(){
        int score = 0;
        //evaluate and sum score for each possible line
        score += evaluateLine(0,0,0,1,0,2);
        score += evaluateLine(1,0,1,1,1,2);
        score += evaluateLine(2,0,2,1,2,2);
        score += evaluateLine(0,0,1,0,2,0);
        score += evaluateLine(0,1,1,1,2,1);
        score += evaluateLine(0,2,1,2,2,2);
        score += evaluateLine(0,0,1,1,2,2);
        score += evaluateLine(0,2,1,1,2,0);
        return score;
    }

    /*
     * heuristic eval function
     * returns:
     * +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
     * -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
     * 0 otherwise
     */
    private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3){
        int score = 0;

        // checking first cell
        if(grid[row1][col1] == playerNum){ //if piece is mine
            score = 1;
        }else if(grid[row1][col1] != 0){ //if piece is opponent
            score = -1;
        }

        //checking second cell
        if(grid[row2][col2] == playerNum){ //if piece is mine
            if (score == 1){ //first cell is my piece
                score = 10;
            } else if (score == -1){ // first cell is player 1 piece
                return 0;
            } else{ //first cell is empty;
                score = 1;
            }
        }else if (grid[row2][col2] != 0){ //if piece is opponent
            if (score == -1){//first cell is player 1
                score = -10;
            } else if (score ==1){ //first cell is my piece
                return 0; //blocked!
            } else{ //first cell is empty
                score = -1;
            }
        }

        //checking third cell
        if(grid[row3][col3] == playerNum){ //if piece is mine
            if(score > 0){//cell 1 and/or 2 are my piece
                return (score*10);
            }else if(score < 0){ //cell 1 and/or 2 are player 1
                return 0;
            } else{ //cells 1/2 are empty
                return 1;
            }
        }else if (grid[row3][col3] != 0){ // if piece is oppenent
            if(score < 0){ //cell 1 and/or 2 are player 1
                return (score *10);
            }else if (score > 1){ //cell 1 and/or 2 are my pieces
                return 0;
            }else{ // cells 1/2 are empty
                return -1;
            }
        }

        return score;
    }
}
