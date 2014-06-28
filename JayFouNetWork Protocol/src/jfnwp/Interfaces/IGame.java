package jfnwp.Interfaces;

import jfnwp.Exception.GameFullException;
import jfnwp.Exception.MoveException;
import jfnwp.Implementation.Message;
import jfnwp.Implementation.Player;
import jfnwp.Services.MessageService;

/**
 * Interface IGame.
 * @version 0.1
 */
public interface IGame {

	public void applyMove(IMove m) throws MoveException;
	public boolean isOver();
	public boolean isGameFull();
	public void gameOver(String ip);
	public void getWinner();
	public Player getWinnerPlayer();
	void addPlayerList(Player p) throws GameFullException;
	int getNbMaxPlayer();
	int getNbMinPlayer();
	int getTimeOut();
	Player getPlayerById(String ip);
	String getNextPlayerToMoveIp(String ip);
	void sendWait(String ip);
	void sendResult(String result, String ip);
	void askMove(String ip);
	void sendError(String txt, String ip);
}
