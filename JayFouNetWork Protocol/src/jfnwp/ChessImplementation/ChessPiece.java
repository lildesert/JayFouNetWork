package jfnwp.ChessImplementation;

import jfnwp.Games.Chess;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.Piece;

public abstract class ChessPiece extends Piece {

	public ChessPiece(Color color) {
		super(color);
	}
	
	public abstract Boolean checkMove(ChessMove mv, Chess board);
	
	public String toString() {
        String lettre = "B";
        if(this.color == Color.White )
            lettre = "W";
        return lettre + this.getClass().getSimpleName();
    }
}
