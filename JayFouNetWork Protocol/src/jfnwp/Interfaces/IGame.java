package jfnwp.Interfaces;

import jfnwp.Exception.MoveException;
import jfnwp.Implementation.Player;

/**
 * Interface IGame.
 * @version 0.1
 */
public interface IGame {

	public void applyMove(IMove m) throws MoveException;
	public boolean isOver();
	public void gameOver();
	public void getWinner();
	public Player getWinnerPlayer();
}
