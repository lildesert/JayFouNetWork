package jfnwp.Interfaces;

import jfnwp.Interfaces.IMove;
import jfnwp.Implementation.Player;

public interface IBoard {
	public void applyMove(Player j, IMove m);
	public boolean checkMove(Player j, IMove m);
	public Color isFinish();
}
