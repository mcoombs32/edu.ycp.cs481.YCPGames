package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/4/13.
 */
public abstract class Board {
	private static final String TAG = "YCPGamesBoard";
	int[][] grid = new int[3][3];
	Settings settings = Settings.getInstance();

	public Board() {

	}

	public void makeMove(int startX, int startY, int endX, int endY) {

	}

	public boolean checkMove(int startX, int startY, int endX, int endY) {

		return true;
	}

	public void placePiece(int x, int y, int player) {
		grid[x][y] = player;
	}

	public boolean checkSpace(int x, int y) {

		return true;
	}

	public int getPieceAt(int x, int y) {
		return 0;
	}



	public void reset() {

	}

	/*
	 * returns the grid
	 * primary purpose is passing grid to AI
	 */
	public int[][] getGrid() {
		return grid;
    }

    public abstract int getGridHeight();

    public abstract int getGridWidth();
}
