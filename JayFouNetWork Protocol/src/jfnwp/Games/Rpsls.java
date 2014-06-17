package jfnwp.Games;

import Moves.RpslsMove;
import jfnwp.Implementation.Game;
import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IMove;

public class Rpsls extends Game {
	
	RpslsMove waitingMove;
	
	public Rpsls()
	{
		nbMaxPlayer = 2;
	}

	@Override
	public void applyMove(IMove m) {
		if(waitingMove == null)
		{
			waitingMove = (RpslsMove) m;
		}
		else
		{
			RpslsMove mv = (RpslsMove) m;
			int resp = mv.getC().compareTo(waitingMove.getC());
		}
		
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getWinner() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player getWinnerPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
