package edu.ycp.cs481.ycpgames;

/**
 * Created by admin on 10/29/13.
 */
public class DotsNode {
	GameVal up = GameVal.EMPTY;
	GameVal down = GameVal.EMPTY;
	GameVal left = GameVal.EMPTY;
	GameVal right = GameVal.EMPTY;
	GameVal playerClaim = GameVal.EMPTY;
	public DotsNode(){

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
