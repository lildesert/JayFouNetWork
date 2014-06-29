package jfnwp.Implementation;

import jfnwp.Interfaces.IMove;

public abstract class Move implements IMove {

	protected String playerIp;
	
	public abstract void deserialize(String d);
	
	@Override
	public void setPlayerIp(String ip) {
		playerIp = ip;
	}

	public String getPlayerIp() {
		return playerIp;
	}
}
