package jfnwp.rpslsImplementation;

import jfnwp.Implementation.Player;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.IBoard;
import jfnwp.Interfaces.IMove;

public class RpslsBoard implements IBoard {
	
	private Color [] board;
	
	public RpslsBoard(){
		this.board = new Color[2];
	}
	
	@Override
	public void applyMove(Player j, IMove m) {		
	}

	@Override
	public boolean checkMove(Player j, IMove m) {
		return false;
	}
	
}
