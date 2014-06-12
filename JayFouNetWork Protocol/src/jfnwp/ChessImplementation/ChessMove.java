package jfnwp.ChessImplementation;

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
	
	public ChessMove(String sMove) {
		String[] splitString = sMove.split("/", 20);
		this.from = new Position(splitString[0]);
		this.to = new Position(splitString[1]);
	}
	
	public ChessMove(Position from, Position to) {
		this.from = from;
		this.to = to;
	}
	
	/**
	 * Check if the object move is not a real move : same place.
	 * @return Boolean
	 * @version 1.0
	 */
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
	
	/**
	 * example : (0,9)(1,9)
	 * @return String 
	 * @version 1.0
	 */
	public String toString() {
		return from.toString() + "/" + to.toString();
	}
}