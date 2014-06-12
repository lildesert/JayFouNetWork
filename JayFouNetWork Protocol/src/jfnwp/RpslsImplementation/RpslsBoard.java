package jfnwp.RpslsImplementation;

import java.util.HashMap;

import jfnwp.Implementation.Game;
import jfnwp.Implementation.Player;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.IMove;

public class RpslsBoard extends Game {
	
	private HashMap<Color, IMove> board;
	private HashMap<Color, Integer> results;
	private int rounds;
	
	public RpslsBoard(){
		board = new HashMap<Color, IMove>();
		rounds = 3;
	}
	
	public RpslsBoard(int rounds){
		board = new HashMap<Color, IMove>();
		this.rounds = rounds = 3;
	}
	
	@Override
	public void applyMove(IMove m) {
		if(checkMove(m)){
			
		}
	}

	public boolean checkMove(IMove m) {
		return true;
	}

	@Override
	public boolean isOver() {
		return false;
	}

	@Override
	public void getWinner() {
		
	}

	@Override
	public void gameOver() {
		
	}

	@Override
	public Player getWinnerPlayer() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
