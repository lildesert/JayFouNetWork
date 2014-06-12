package jfnwp.ChessImplementation;

import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.Piece;

public abstract class ChessPiece extends Piece {

	public ChessPiece(Color color) {
		super(color);
	}
	
	public abstract Boolean checkMove(ChessMove mv, ChessGame board);
	
	public String toString() {
        String lettre = "B";
        if(this.color == Color.White )
            lettre = "W";
        return lettre + this.getClass().getSimpleName();
    }
}
