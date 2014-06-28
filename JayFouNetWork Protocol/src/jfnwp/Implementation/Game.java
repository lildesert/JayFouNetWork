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

public abstract class Game implements IGame {

	private static Logger logger = LogManager.getLogger(Game.class.getName());

	protected int nbMaxPlayer;
	protected int nbMinPlayer = 2;
	protected int timeOut = 60;
	protected List<Player> playerList = new ArrayList<Player>();

	public abstract void applyMove(IMove m) throws MoveException;

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

	public void addPlayerList(Player p) throws GameFullException {
		if (playerList.size() < nbMaxPlayer) {
			playerList.add(p);
		} else {
			throw new GameFullException(
					"Game is full, can't add another player");
		}
	}

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

	public void sendWait(String ip) {
		logger.info("sendWait call " + ip);
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Wait("Wait for your turn please");
	}
	
	public void sendError(String txt, String ip)
	{
		logger.info("sendError call " + ip);
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Error(txt);
	}

	public void sendResult(String result, String ip) {
		logger.info("send move result call");
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Result(result);
	}

	public void askMove(String ip) {
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Move();
	}

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