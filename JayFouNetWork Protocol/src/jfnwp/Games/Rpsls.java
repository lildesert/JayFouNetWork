package jfnwp.Games;

import java.net.Socket;

import jfnwp.Implementation.Game;
import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IMove;
import jfnwp.Moves.RpslsMove;
import jfnwp.Services.MessageService;

public class Rpsls extends Game {
	
	RpslsMove waitingMove;
	
	public Rpsls()
	{
		nbMaxPlayer = 2;
	}

	@Override
	public void applyMove(IMove m) {
		Socket s = null;
		if(waitingMove == null)
		{
			waitingMove = (RpslsMove) m;
			sendWait(waitingMove.getPlayerIp());
		}
		else
		{
			RpslsMove mv = (RpslsMove) m;
			int resp = mv.getC().compareTo(waitingMove.getC());
			
			if(resp == 1)
			{
				sendMoveResult("win", mv.getPlayerIp());
				sendMoveResult("loose", waitingMove.getPlayerIp());		
			}
			else
			{
				sendMoveResult("loose", mv.getPlayerIp());
				sendMoveResult("win", waitingMove.getPlayerIp());
			}
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
