package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 10/28/13.
 */
public class DotsBoard extends Board {


	/**
	 *
	 * @return grid height
	 */
	@Override
	public int getGridHeight(){
		return grid.length;
	}

	/**
	 *
	 * @return grid width
	 */
	@Override
	public int getGridWidth(){
		return grid[0].length;
	}
}
