package jfnwp.Server;

import java.util.ArrayList;
import java.util.List;

import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IGame;

public class Info {

	private List<GameContext> gameList = new ArrayList<GameContext>();

	private Info() {
	}

	public final static Info Instance = new Info();

	public synchronized List<GameContext> getGameList() {
		return gameList;
	}

	public synchronized void addGameList(GameContext g) {
		this.gameList.add(g);
	}

	public synchronized GameContext getExistingGame(Class c) {
		GameContext gc = null;
		boolean end = false;
		int i = 0;
		while (end == false) {
			if (gameList.get(i).getGame().getClass() == c) {
				if (!gameList.get(i).getGame().isGameFull()) {
					end = true;
					gc = gameList.get(i);
				}
			}
		}

		return gc;
	}
}
