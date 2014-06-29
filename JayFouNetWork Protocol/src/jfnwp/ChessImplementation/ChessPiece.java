package jfnwp.ChessImplementation;

import jfnwp.Chess.Color;
import jfnwp.Chess.Piece;
import jfnwp.Games.Chess;
import jfnwp.Moves.ChessMove;

/**
 * @see Piece
 * A piece for chess with color
 * @version 1.0
 */
public abstract class ChessPiece extends Piece {

	public ChessPiece(Color color) {
		super(color);
	}
	
	/**
	 * Check if a movement is valid
	 */
	public abstract Boolean checkMove(ChessMove mv, Chess board);
	
	/**
	 * A string from a piece (with color)
	 * @version 1.0
	 */
	public String toString() {
        String lettre = "B";
        if(this.color == Color.White )
            lettre = "W";
        return lettre + this.getClass().getSimpleName();
    }
}
