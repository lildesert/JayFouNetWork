package jfnwp.chessImplementation;

import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.Piece;

public abstract class ChessPiece extends Piece {

	public ChessPiece(Color color) {
		super(color);
	}
	
	public abstract Boolean checkMove(ChessMove mv, ChessBoard board);
}
