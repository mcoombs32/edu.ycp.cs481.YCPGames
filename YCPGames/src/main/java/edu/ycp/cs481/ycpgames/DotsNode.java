package edu.ycp.cs481.ycpgames;

/**
 * Created by admin on 10/29/13.
 */
public class DotsNode {
	BoardVal up = BoardVal.EMPTY;
	BoardVal down = BoardVal.EMPTY;
	BoardVal left = BoardVal.EMPTY;
	BoardVal right = BoardVal.EMPTY;
	BoardVal playerClaim = BoardVal.EMPTY;
	public DotsNode(){

	}
	public BoardVal getVal(Direction d){
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
	public void setVal(Direction d, BoardVal v){
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
		if((up != BoardVal.EMPTY) && (down != BoardVal.EMPTY) && (left != BoardVal.EMPTY) && (right != BoardVal.EMPTY)){
			playerClaim = v;
		}
	}

	public BoardVal isNodeFilled(){
		return playerClaim;
	}
}
