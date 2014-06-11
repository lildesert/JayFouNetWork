package jfnwp.Server;

import java.util.ArrayList;
import java.util.List;

import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IGame;

public class Info {

	
	private List<GameContext> gameList = new ArrayList<GameContext>();

	private Info()
	{
	}

	public final static Info Instance = new Info();
	
	public synchronized List<GameContext> getGameList() {
		return gameList;
	}

	public synchronized void addGameList(GameContext g) {
		this.gameList.add(g);
	}
}
