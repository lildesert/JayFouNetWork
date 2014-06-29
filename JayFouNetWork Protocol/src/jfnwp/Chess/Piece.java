package jfnwp.Chess;

/**
 * Represent a piece in a game with a color 
 * for each opponent
 * @version 1.0
 */
public abstract class Piece {
	protected Color color;
	
	public Piece(Color color){
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
}