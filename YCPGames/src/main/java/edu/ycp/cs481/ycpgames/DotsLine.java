package edu.ycp.cs481.ycpgames;

/**
 * Created by brian on 11/14/13.
 */
public class DotsLine {
	private GameVal lineVal;
	public DotsLine(){
		lineVal = GameVal.EMPTY;
	}

	/**
	 *
	 * @param lineVal value to set lineVal to
	 */
	public void setLineVal(GameVal lineVal) {
		this.lineVal = lineVal;
	}

	/**
	 *
	 * @return returns lineVal
	 */
	public GameVal getLineVal(){
		return lineVal;
	}
}
