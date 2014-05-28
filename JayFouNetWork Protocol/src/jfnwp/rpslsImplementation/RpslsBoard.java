package jfnwp.rpslsImplementation;

import java.util.HashMap;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.IBoard;
import jfnwp.Interfaces.IMove;

public class RpslsBoard implements IBoard {
	
	private HashMap<Color, IMove> board;
	private HashMap<Color, Integer> results;
	private int rounds;
	
	public RpslsBoard(){
		board = new HashMap<Color, IMove>();
		rounds = 3;
	}
	
	public RpslsBoard(int rounds){
		board = new HashMap<Color, IMove>();
		this.rounds = rounds = 3;
	}
	
	@Override
	public void applyMove(Color color, IMove m) {
		if(board.get(color) != null)
			board.remove(color);
		board.put(color, m);
	}

	@Override
	public boolean checkMove(Color color, IMove m) {
		return true;
	}

	@Override
	public Boolean isFinish(Color turn) {
		if(rounds == 0)
			return true;
		return false;
	}

	@Override
	public Color getWinner() {
		if(results.get(Color.White) > results.get(Color.Black)) {
			return Color.White;
		} else if (results.get(Color.White) < results.get(Color.Black)) {
			return Color.Black;
		} 
		return Color.Neutral;
	}
	
}
