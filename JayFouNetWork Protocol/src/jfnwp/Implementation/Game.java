package jfnwp.Implementation;

import jfnwp.Interfaces.IGame;
import jfnwp.Interfaces.IMove;

public class Game implements IGame {

	private String name;
	private int nbMaxPlayer;
	private int timeOut;
	private Rules r;
	
	@Override
	public void applyMove(IMove m) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void getWinner() {
		// TODO Auto-generated method stub
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNbMaxPlayer() {
		return nbMaxPlayer;
	}

	public void setNbMaxPlayer(int nbMaxPlayer) {
		this.nbMaxPlayer = nbMaxPlayer;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public Rules getR() {
		return r;
	}

	public void setR(Rules r) {
		this.r = r;
	}
}
