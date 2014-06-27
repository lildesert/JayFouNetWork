package jfnwp.ChessImplementation;

import jfnwp.Chess.Color;
import jfnwp.Chess.Piece;
import jfnwp.Games.Chess;
import jfnwp.Moves.ChessMove;

public abstract class ChessPiece extends Piece {

	public ChessPiece(Color color) {
		super(color);
	}
	
	//Ou est l'impl�mentation de cette m�thode ??? (Fait tout p�ter)
	public abstract Boolean checkMove(ChessMove mv, Chess board);
	
	public String toString() {
        String lettre = "B";
        if(this.color == Color.White )
            lettre = "W";
        return lettre + this.getClass().getSimpleName();
    }
}
