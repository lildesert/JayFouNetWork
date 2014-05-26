package jfnwp.Implementation;

import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.IPosition;

public class ChessMove implements IMove{

	private IPosition from;
	private IPosition to;
	
	public ChessMove() {
		this.from = new Position();
		this.to = new Position();
	}
	
	public ChessMove(IPosition from, IPosition to) {
		this.from = from;
		this.to = to;
	}
	
	public IPosition getFrom() {
		return from;
	}
	
	public void setFrom(IPosition from) {
		this.from = from;
	}
	
	public IPosition getTo() {
		return to;
	}
	
	public void setTo(IPosition to) {
		this.to = to;
	}
}
