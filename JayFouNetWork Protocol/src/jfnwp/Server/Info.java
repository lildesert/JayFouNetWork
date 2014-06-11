package jfnwp.Server;

import java.util.ArrayList;
import java.util.List;

import jfnwp.Implementation.Player;

public class Info {

	private List<Player> playerList = new ArrayList<Player>();

	private Info()
	{
	}

	public final static Info Instance = new Info();

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void addPlayerList(Player p) {
		this.playerList.add(p);
	}
}
