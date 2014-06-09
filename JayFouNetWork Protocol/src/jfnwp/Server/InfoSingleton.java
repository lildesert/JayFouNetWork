package jfnwp.Server;

import java.util.LinkedList;
import java.util.List;

import jfnwp.Implementation.Player;

public class InfoSingleton {

	private List<Player> playerList;

	private InfoSingleton()
	{
		playerList = new LinkedList<Player>();
	}

	private static InfoSingleton INSTANCE = null;

	public static InfoSingleton getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new InfoSingleton();
		}
		return INSTANCE;
	}
	
	public List<Player> getPlayerList() {
		return playerList;
	}

	public void addPlayerList(Player p) {
		this.playerList.add(p);
	}
}
