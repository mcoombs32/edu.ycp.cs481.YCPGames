package edu.ycp.cs481.ycpgames;

import java.util.Random;
import java.util.Set;

/**
 * Created by brian on 10/4/13.
 */
public class TicTacToeAI extends Player {
    private int difficulty;
    Random rand;
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
    }

    public int[] makeMove(int [][] grid){
        int[] move = {0,0};

        switch (difficulty){
            case 0://difficulty low (very very low)
                move = randMove(grid);
                break;
            case 1://difficulty medium (prefers some moves over others, but will make no attempt to
                   // block player 1 win
                move = lookupTable(grid);
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
    private int[] randMove(int[][] grid){
        int[] move = {0,0};
        do{
            move[0] = rand.nextInt(3);
            move[1] = rand.nextInt(3);
        }while(!confirmValidMove(grid, move));

        return move;
    }

    /*
     * helper method to ensure that a move is valid
     */
    private boolean confirmValidMove(int[][] grid, int[] move){
        if(grid[move[0]][move[1]] == 0){
            return true;
        }
        return false;
    }

    private int[] lookupTable(int[][] grid){
        for(int[] move : preferredMoves){
            if (grid[move[0]][move[1]] == 0){
                return move;
            }
        }
        //no empty spaces
        return null;
    }
}
