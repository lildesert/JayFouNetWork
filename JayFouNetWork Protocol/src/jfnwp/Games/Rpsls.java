package jfnwp.Games;

import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Implementation.Game;
import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IMove;
import jfnwp.Moves.RpslsMove;
import jfnwp.RpslsImplementation.ListenerRpsls;
import jfnwp.Services.MessageService;

public class Rpsls extends Game {
	
	private RpslsMove waitingMove;
	private static Logger logger = LogManager.getLogger(Rpsls.class
			.getName());
	
	public Rpsls()
	{
		nbMaxPlayer = 2;
	}

	@Override
	public void applyMove(IMove m) {
		logger.info("apply move de Rpsls appel�");
		Socket s = null;
		if(waitingMove == null)
		{
			logger.info("entr�e dans le waitingMove");
			waitingMove = (RpslsMove) m;
			logger.info("waitingMove cr��");
			sendWait(waitingMove.getPlayerIp());
			logger.info("wait envoy�");
			askMove(getNextPlayerToMoveIp(waitingMove.getPlayerIp()));
			logger.info("move opponent asked");
		}
		else
		{
			RpslsMove mv = (RpslsMove) m;
			int resp = mv.getC().compareTo(waitingMove.getC());
			
			if(resp == 1)
			{
				sendResult("win", mv.getPlayerIp());
				askMove(mv.getPlayerIp());
				sendResult("loose", waitingMove.getPlayerIp());
				sendWait(waitingMove.getPlayerIp());
			}
			else
			{
				sendResult("loose", mv.getPlayerIp());
				sendWait(mv.getPlayerIp());
				sendResult("win", waitingMove.getPlayerIp());
				askMove(mv.getPlayerIp());
			}
			
			waitingMove = null;
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
