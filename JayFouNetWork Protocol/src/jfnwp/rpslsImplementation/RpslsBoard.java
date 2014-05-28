package jfnwp.rpslsImplementation;

import java.util.HashMap;

import jfnwp.Implementation.Player;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.IBoard;
import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.Piece;

public class RpslsBoard implements IBoard {
	
	private HashMap<Color, Piece> board;
	
	public RpslsBoard(){
		board = new HashMap<Color, Piece>();
	}
	
	@Override
	public void applyMove(Player j, IMove m) {
		
	}

	@Override
	public boolean checkMove(Player j, IMove m) {
		
	}

	@Override
	public Boolean isFinish(Color turn) {
		return null;
	}
	
}
