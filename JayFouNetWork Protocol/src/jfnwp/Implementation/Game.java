package jfnwp.Implementation;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Exception.GameFullException;
import jfnwp.Exception.MoveException;
import jfnwp.Interfaces.IGame;
import jfnwp.Interfaces.IMove;
import jfnwp.Services.MessageService;

/**
 * Implementation of IGame
 * Manage common function in all games
 * @version 1.0
 */
public abstract class Game implements IGame {

	// Log all the events
	private static Logger logger = LogManager.getLogger(Game.class.getName());

	protected int nbMaxPlayer;
	protected int nbMinPlayer = 2;
	protected int timeOut = 60;
	protected List<Player> playerList = new ArrayList<Player>();

	public abstract void applyMove(IMove m) throws MoveException;
	
	/**
	 * Stop a game
	 * @param String ip
	 * @version 1.0
	 */
	public void gameOver(String ip)
	{
		String ipOpponent = getNextPlayerToMoveIp(ip);
		logger.info("gameOver call " + ipOpponent);
		Socket s = getPlayerById(ipOpponent).getSock();
		MessageService m = new MessageService(s);
		m.End();
	}

	public abstract void getWinner();

	public abstract Player getWinnerPlayer();
	
	/**
	 * Check if a game is full 
	 * @version 1.0
	 */
	public boolean isGameFull() {
		boolean resp = false;
		if (playerList.size() >= nbMaxPlayer) {
			resp = true;
		}

		return resp;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * Add a player in the game
	 * @version 1.0
	 * @param Player p
	 */
	public void addPlayerList(Player p) throws GameFullException {
		if (playerList.size() < nbMaxPlayer) {
			playerList.add(p);
		} else {
			throw new GameFullException(
					"Game is full, can't add another player");
		}
	}
	
	/** 
	 * Get the player with the ip given in parameter
	 * @version 1.0
	 * @param String IP
	 * @return String of the player
	 */
	public String getNextPlayerToMoveIp(String ip) {
		String nextIp = "";
		boolean find = false;
		int i = 0;
		while (i < playerList.size() || find == false) {
			if (playerList.get(i).getAddress() != ip) {
				find = true;
				nextIp = playerList.get(i).getAddress().toString();
			}
			i++;
		}
		logger.info("getNextPlayerOk");
		return nextIp;
	}
	
	/**
	 * Ask to the players in waiting list to wait for others players
	 * @param String IP
	 * @version 1.0
	 */
	public void sendWait(String ip) {
		logger.info("sendWait call " + ip);
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Wait("Wait for your turn please");
	}
	
	/**
	 * Warn player of an error
	 * @version 1.0
	 * @param String txt : error
	 * @param String ip 
	 */
	public void sendError(String txt, String ip)
	{
		logger.info("sendError call " + ip);
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Error(txt);
	}
	
	/**
	 * Send the result
	 * @version 1.0
	 * @param String result
	 * @param String ip 
	 */
	public void sendResult(String result, String ip) {
		logger.info("send move result call");
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Result(result);
	}
	
	/**
	 * Ask a move to the player with the given ip
	 * @version 1.0
	 * @param String ip 
	 */
	public void askMove(String ip) {
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Move();
	}
	
	/**
	 * @version 1.0
	 * @param String ip 
	 */
	public Player getPlayerById(String ip) {
		Player p = null;
		int i = 0;
		boolean end = false;
		while (i < playerList.size() || end == false) {
			if (playerList.get(i).getAddress() == ip) {
				p = playerList.get(i);
				end = true;
			}
			i++;
		}

		return p;
	}

	public int getNbMaxPlayer() {
		return nbMaxPlayer;
	}

	public int getNbMinPlayer() {
		return nbMinPlayer;
	}

	public int getTimeOut() {
		return timeOut;
	}

}