package jfnwp.Games;

import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jfnwp.Client.ClientListener;
import jfnwp.Implementation.Game;
import jfnwp.Implementation.Player;
import jfnwp.Interfaces.IMove;
import jfnwp.Moves.RpslsMove;
import jfnwp.Services.MessageService;

public class Rpsls extends Game {

	private RpslsMove waitingMove;
	private static Logger logger = LogManager.getLogger(Rpsls.class.getName());

	public Rpsls() {
		nbMaxPlayer = 2;
	}
	
	/**
	 * Main function.
	 * Apply a move.
	 * @param Imove m
	 * @version 1.0
	 */
	@Override
	public void applyMove(IMove m) {
		logger.info("apply move of Rpsls");
		Socket s = null;
		if (waitingMove == null) {
			logger.info("entry in waitingMove");
			waitingMove = (RpslsMove) m;
			logger.info("waitingMove created");
			sendWait(waitingMove.getPlayerIp());
			logger.info("wait sent");
			askMove(getNextPlayerToMoveIp(waitingMove.getPlayerIp()));
			logger.info("move opponent asked");
		} else {
			RpslsMove mv = (RpslsMove) m;
			int resp = mv.getC().compareTo(waitingMove.getC());

			switch (resp) {
			case 0:
				sendResult("loose", mv.getPlayerIp());
				sendWait(mv.getPlayerIp());
				sendResult("win", waitingMove.getPlayerIp());
				askMove(mv.getPlayerIp());
				break;
			case 1:
				sendResult("win", mv.getPlayerIp());
				askMove(mv.getPlayerIp());
				sendResult("loose", waitingMove.getPlayerIp());
				sendWait(waitingMove.getPlayerIp());
				break;
			case 2:
				sendResult("tie", mv.getPlayerIp());
				askMove(mv.getPlayerIp());
				sendResult("tie", waitingMove.getPlayerIp());
				sendWait(waitingMove.getPlayerIp());
				break;
			case -1:
				String err = "Rpsls fatal error";
				sendError(err, mv.getPlayerIp());
				sendError(err, waitingMove.getPlayerIp());
				break;
			}

			waitingMove = null;
		}

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
