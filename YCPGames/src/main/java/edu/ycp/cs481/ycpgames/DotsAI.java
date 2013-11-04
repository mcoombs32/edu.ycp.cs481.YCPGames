package edu.ycp.cs481.ycpgames;

import android.util.Log;

/**
 * Created by brian on 10/28/13.
 */
public class DotsAI extends Player{
	private int difficulty;
	private int [][] grid;
	public DotsAI(int num){
		super(num);
		difficulty = settings.getDifficulty();
		setHumanPlayer(false);
	}

	/**
	 *
	 * @param g    a snapshot of the gird
	 * @return int[] move
	 * 					move[0] x position of move
	 * 					move[1] y position of move
	 * 					move[2] direction of move
	 * 						0 = up
	 * 						1 = down
	 * 						2 = left
	 * 						3 = right
	 */
	@Override
	public int[] makeMove(int[][] g){
		int[] move = {0, 0};
		grid = g;
		switch (difficulty) {
			case 0://difficulty low

				break;
			case 1://difficulty medium

				break;
			case 2://difficulty high

				break;
			default://error
				move[0] = -1;
				move[1] = -1;
				break;
		}


		return move;
	}
}
