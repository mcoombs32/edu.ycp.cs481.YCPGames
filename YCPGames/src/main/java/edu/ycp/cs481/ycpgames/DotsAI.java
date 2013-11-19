package edu.ycp.cs481.ycpgames;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 10/28/13.
 */
public class DotsAI extends Player {
	private int difficulty;
	private DotsNode [][] grid;
	private GameVal player, opponent;
	public DotsAI(int num){
		super(num);
		difficulty = settings.getDifficulty();
		setHumanPlayer(false);
		if(num == 1){
			player = GameVal.PLAYER_ONE;
			opponent = GameVal.PLAYER_TWO;
		}else{
			player = GameVal.PLAYER_TWO;
			opponent = GameVal.PLAYER_ONE;
		}

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
	public int[] makeDotsMove(DotsNode[][] g){
		int[] move = {0, 0};
		int[] result;
		grid = g;
		switch (difficulty) {
			case 0://difficulty low
				result = minimax(2, player);
				move[0] = result[1];
				move[1] = result[2];
				move[2] = result[3];
				break;
			case 1://difficulty medium
				result = minimax(4, player);
				move[0] = result[1];
				move[1] = result[2];
				move[2] = result[3];
				break;
			case 2://difficulty high
				result = minimax(8, player);
				move[0] = result[1];
				move[1] = result[2];
				move[2] = result[3];
				break;
			default://error
				move[0] = -1;
				move[1] = -1;
				move[2] = -1;
				break;
		}

		return move;
	}

	/**
	 * Recursive  minimax method for finding best move
	 *
	 * @param depth  level in the tree
	 * @param whosTurn active player
	 * @return array of best score, best x position, best y position, best direction
	 */
	private int[] minimax(int depth, GameVal whosTurn){
		//Generate all possible moves
		List<int[]> moves = generateMoves();
		//set goal
		int bestScore;
		if (whosTurn == player) {
			bestScore = Integer.MIN_VALUE;
		} else {
			bestScore = Integer.MAX_VALUE;
		}
		int currentScore;
		int bestX = -1;
		int bestY = -1;
		int bestD = -1;

		if (moves.isEmpty() || depth == 0) {
			//either game over, or depth reached
			bestScore = evaluate(whosTurn);
		}else{
			//evaluate branches
			for(int[]move : moves){
				grid[move[0]][move[1]].setVal(intToDirection(move[2]), whosTurn);
				if(whosTurn == player){
					//simulating AI turn
					currentScore = minimax(depth-1, opponent)[0];
					//if these has a better score then use it
					if (currentScore > bestScore) {
						bestScore = currentScore;
						bestX = move[0];
						bestY = move[1];
					}
				}else{
					//simulating opponent turn
					currentScore = minimax(depth-1, player)[0];
					//if these has a better score then use it
					if (currentScore < bestScore) {
						bestScore = currentScore;
						bestX = move[0];
						bestY = move[1];
					}
				}
				//undo move
				grid[move[0]][move[1]].setVal(intToDirection(move[2]), GameVal.EMPTY);
			}
		}

		return new int[]{bestScore, bestX, bestY, bestD};
	}
	private Direction intToDirection(int dir){
		switch (dir){
			case 0:
				return Direction.UP;
			case 1:
				return Direction.DOWN;
			case 2:
				return Direction.LEFT;
			case 3:
				return Direction.RIGHT;
			default:
				return null;
		}
	}
	/**
	 * helper method to generate all currently availible moves
	 * @return a ArrayList of all availible moves
	 */
	private List<int[]> generateMoves(){
		List<int[]> moves = new ArrayList<int[]>();
		for(int x = 0; x < settings.getGridSize()-1; x++){
			for(int y = 0; y < settings.getGridSize()-1; y++){
				/*logic for generating move
				 *need to convert from int into GameVal
				 *
				 * move[0] x position of move
				 * move[1] y position of move
				 * move[2] direction of move
				 * 			0 = up
				 * 			1 = down
				 * 			2 = left
				 * 			3 = right
				 * this isn't the most elegant method but it works
				 */
				if(grid[x][y].getVal(Direction.UP) == GameVal.EMPTY){
					moves.add(new int[] {x,y,0});
				}
				if(grid[x][y].getVal(Direction.LEFT) == GameVal.EMPTY){
					moves.add(new int[] {x,y,2});
				}
				if(y == 0){
					if(grid[x][y].getVal(Direction.DOWN) == GameVal.EMPTY){
						moves.add(new int[] {x,y,1});
					}
				}
				if(x == 0){
					if(grid[x][y].getVal(Direction.RIGHT) == GameVal.EMPTY){
						moves.add(new int[] {x,y,3});
					}
				}
			}
		}
		return moves;
	}

	/**
	 * evaluation function for the current board state
	 *
	 * @return the score for the current board state
	 */
	private int evaluate(GameVal whosTurn){
		int score = 0;

		//run evaluate node for each node on board
		for(int x = 0; x < settings.getGridSize(); x++){
			for(int y = 0; y < settings.getGridSize(); y++){
				score += evaluateNode(x,y,whosTurn);
			}
		}

		return score;
	}

	/**
	 * ranks a node based on how many lines it has around it based on current turn
	 *
	 * @param nodeX    x location of node
	 * @param nodeY    y location of node
	 * @param whosTurn current turn in simulation
	 * @return			rank for node
	 */
	private int evaluateNode(int nodeX, int nodeY, GameVal whosTurn){
		int sides = 0;
		//first find out how many sides
		if(grid[nodeX][nodeY].getVal(Direction.UP) != GameVal.EMPTY){
			sides ++;
		}
		if(grid[nodeX][nodeY].getVal(Direction.DOWN) != GameVal.EMPTY){
			sides ++;
		}
		if(grid[nodeX][nodeY].getVal(Direction.LEFT) != GameVal.EMPTY){
			sides ++;
		}
		if(grid[nodeX][nodeY].getVal(Direction.RIGHT) != GameVal.EMPTY){
			sides ++;
		}
		if(whosTurn == player){
			//evaluate AI move and rank accordingly
			switch (sides){
				case 0:
					return 0;
				case 1:
					return -1;
				case 2:
					return 10;
				case 3:
					//this is a bad move, sets opponent up to capture
					return -100;
				case 4:
					//rank according to who claimed node
					if(grid[nodeX][nodeY].isNodeFilled() == player){
						return 100;
					}else{
						return -100;
					}
				default:
					return 0;
			}
		}else{
			//evaluate opponent move and rank accordingly
			switch (sides){
				case 0:
					return 0;
				case 1:
					return 1;
				case 2:
					return -10;
				case 3:
					//this is a good move, sets AI up to capture
					return 100;
				case 4:
					//rank according to who claimed node
					if(grid[nodeX][nodeY].isNodeFilled() != player){
						return 100;
					}else{
						return -100;
					}
				default:
					return 0;
			}
		}
	}
}
