package jfnwp.Moves;

import jfnwp.Implementation.Move;
import jfnwp.Implementation.Position;

public class ChessMove extends Move{

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

	@Override
	public void serialize(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deserialize(String d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSerializedData() {
		// TODO Auto-generated method stub
		return null;
	}
}