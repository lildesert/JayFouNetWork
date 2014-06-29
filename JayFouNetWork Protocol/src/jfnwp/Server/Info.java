package jfnwp.Server;

import java.util.ArrayList;
import java.util.List;

import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IGame;

/**
 * Information about a game
 * @version 1.0
 */
public class Info {

	private List<GameContext> gameList = new ArrayList<GameContext>();
	
	private static int port = 10000;
	private static final String host = "228.0.0.0";

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
		if (gameList.size() != 0) {
			while (end == false && i < gameList.size()) {
				if (gameList.get(i).getGame().getClass() == c) {
					if (!gameList.get(i).getGame().isGameFull()) {
						end = true;
						gc = gameList.get(i);
					}
				}
				
				i++;
			}
		}

		return gc;
	}
	
	public synchronized String getHost()
	{
		return host;
	}
	
	public synchronized int getPort()
	{
		return port++;
	}
	
	public synchronized void deleteGame(GameContext gc, String ip)
	{
		for(int i = 0; i < gameList.size(); i++)
		{
			if(gameList.get(i) == gc)
			{
				gameList.get(i).getGame().gameOver(ip);
				gameList.remove(i);
			}
		}
	}
}
