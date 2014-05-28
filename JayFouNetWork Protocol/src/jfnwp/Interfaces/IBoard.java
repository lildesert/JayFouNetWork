package jfnwp.Interfaces;

import jfnwp.Interfaces.IMove;
import jfnwp.Implementation.Player;

public interface IBoard {
	public void applyMove(Color color, IMove m);
	public boolean checkMove(Color color, IMove m);
	public Boolean isFinish(Color turn);
	public Color getWinner();
}
