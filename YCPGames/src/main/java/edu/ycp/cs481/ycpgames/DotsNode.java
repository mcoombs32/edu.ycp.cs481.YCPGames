package edu.ycp.cs481.ycpgames;

import android.util.Log;

/**
 * Created by admin on 10/29/13.
 */
public class DotsNode {
    private static final String TAG = "DotsNode";
    // these should probably be private fields with getters and setters
    public DotsLine up = new DotsLine();
    public DotsLine down = new DotsLine();
    public DotsLine left = new DotsLine();
    public DotsLine right = new DotsLine();
    public GameVal playerClaim = GameVal.EMPTY;

	public DotsNode(){

        playerClaim = GameVal.EMPTY;
    }
	public GameVal getVal(Direction d){
		switch (d){
			case UP:
				return up.getLineVal();
			case DOWN:
				return down.getLineVal();
			case LEFT:
				return left.getLineVal();
			case RIGHT:
				return right.getLineVal();
			default:
                Log.d(TAG, "Default Case");
				return null;
		}
	}
	public void setVal(Direction d, GameVal v){
		switch (d){
			case UP:
				up.setLineVal(v);
				break;
			case DOWN:
				down.setLineVal(v);
				break;
			case LEFT:
				left.setLineVal(v);
				break;
			case RIGHT:
				right.setLineVal(v);
				break;
			default:
				break;
		}
		if((up.getLineVal() != GameVal.EMPTY) && (down.getLineVal() != GameVal.EMPTY) && (left.getLineVal() != GameVal.EMPTY) && (right.getLineVal() != GameVal.EMPTY)){
			playerClaim = v;
		}
	}

	public GameVal isNodeFilled(){
		return playerClaim;
	}
}
