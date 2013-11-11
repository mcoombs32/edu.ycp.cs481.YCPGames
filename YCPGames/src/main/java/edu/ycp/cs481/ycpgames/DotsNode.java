package edu.ycp.cs481.ycpgames;

import android.util.Log;

/**
 * Created by admin on 10/29/13.
 */
public class DotsNode {
    private static final String TAG = "DotsNode";
    // these should probably be private fields with getters and setters
    public GameVal up = GameVal.EMPTY;
    public GameVal down = GameVal.EMPTY;
    public GameVal left = GameVal.EMPTY;
    public GameVal right = GameVal.EMPTY;
    public GameVal playerClaim;
	public DotsNode(){

        playerClaim = GameVal.EMPTY;
    }
	public GameVal getVal(Direction d){
		switch (d){
			case UP:
				return up;
			case DOWN:
				return down;
			case LEFT:
				return left;
			case RIGHT:
				return right;
			default:
                Log.d(TAG, "Default Case");
				return null;
		}
	}
	public void setVal(Direction d, GameVal v){
		switch (d){
			case UP:
				up = v;
				break;
			case DOWN:
				down = v;
				break;
			case LEFT:
				left = v;
				break;
			case RIGHT:
				right = v;
				break;
			default:
				break;
		}
		if((up != GameVal.EMPTY) && (down != GameVal.EMPTY) && (left != GameVal.EMPTY) && (right != GameVal.EMPTY)){
			playerClaim = v;
		}
	}

	public GameVal isNodeFilled(){
		return playerClaim;
	}
}
