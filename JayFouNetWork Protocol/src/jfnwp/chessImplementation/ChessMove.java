package jfnwp.chessImplementation;

import jfnwp.Implementation.Position;
import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.IPosition;

public class ChessMove implements IMove{

	private Position from;
	private Position to;
	
	public ChessMove() {
		this.from = new Position();
		this.to = new Position();
	}
	
	public ChessMove(Position from, Position to) {
		this.from = from;
		this.to = to;
	}
	
	public Boolean noMove() {
		if((from.getX() == to.getX()) && (from.getY() == to.getY()))
			return true;
		return false;
	}
	
	public Position getFrom() {
		return from;
	}
	
	public void setFrom(Position from) {
		this.from = from;
	}
	
	public Position getTo() {
		return to;
	}
	
	public void setTo(Position to) {
		this.to = to;
	}
}