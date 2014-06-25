package jfnwp.Implementation;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import jfnwp.Exception.GameFullException;
import jfnwp.Exception.MoveException;
import jfnwp.Interfaces.IGame;
import jfnwp.Interfaces.IMove;
import jfnwp.Moves.RpslsMove;
import jfnwp.Services.MessageService;

public abstract class Game implements IGame {

	protected int nbMaxPlayer;
	protected int nbMinPlayer = 2;
	protected int timeOut = 60;
	protected List<Player> playerList = new ArrayList<Player>();

	public abstract void applyMove(IMove m) throws MoveException;

	public abstract void gameOver();

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
	
	public void sendWait(String ip) {
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		m.Wait("Wait for your turn please");
	}
	
	public void sendMoveResult(String result, String ip) {
		Socket s = getPlayerById(ip).getSock();
		MessageService m = new MessageService(s);
		IMove mvResult = new RpslsMove();
		mvResult.setMoveResult(result);
		m.Move(mvResult);
	}

	public Player getPlayerById(String ip) {
		Player p = null;
		int i = 0;
		while (i <= playerList.size()) {
			try {
				if (playerList.get(i).getAddress() == InetAddress.getByName(ip)) {
					p = playerList.get(i);
				}
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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