package jfnwp.chessImplementation;

import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;

public class Knight extends ChessPiece {

	public Knight(Color color) {
		super(color);
	}

	@Override
	public Boolean checkMove(ChessMove mv, ChessBoard board) {
        
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
		if( ! board.noPiece(to) && board.samePlayer(from, to))
            return false;
        
        if( (Math.abs(to.getX() - from.getX()) ==  2) && (Math.abs(to.getY() - from.getY()) == 1) ) {
            return true;
        } else if( (Math.abs(to.getX() - from.getX()) ==  1) && (Math.abs(to.getY() - from.getY()) == 2) ) {
            return true;
        }
                
        return false;
	}
}