package jfnwp.Implementation;

import jfnwp.Interfaces.IMove;

/**
 * @version 1.0
 */
public abstract class Move implements IMove {

	protected String playerIp;
	protected String moveResult;
	
	public abstract void deserialize(String d);
	
	@Override
	public void setPlayerIp(String ip) {
		playerIp = ip;
	}

	public String getPlayerIp() {
		return playerIp;
	}
	
	public String getMoveResult() {
		return moveResult;
	}

	public void setMoveResult(String moveResult) {
		this.moveResult = moveResult;
	}
}
