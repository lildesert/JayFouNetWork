package jfnwp.Implementation;

import java.util.ArrayList;
import java.util.List;

import jfnwp.Exception.GameFullException;
import jfnwp.Exception.MoveException;
import jfnwp.Interfaces.IGame;
import jfnwp.Interfaces.IMove;

public abstract class Game implements IGame {

	protected Rules r;
	protected int nbMaxPlayer;
	protected int nbMinPlayer = 2;
	protected int timeOut = 60;
	protected List<Player> playerList = new ArrayList<Player>();

	public abstract void applyMove(IMove m) throws MoveException;

	public abstract void gameOver();

	public abstract void getWinner();
	
	public abstract Player getWinnerPlayer();

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

	public Rules getR() {
		return r;
	}

	public void setR(Rules r) {
		this.r = r;
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