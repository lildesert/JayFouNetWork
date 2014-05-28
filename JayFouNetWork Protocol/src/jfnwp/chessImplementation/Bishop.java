package jfnwp.chessImplementation;

import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;

/**
 * @see ChessPiece
 * @version 1.0
 */
public class Bishop extends ChessPiece {

	public Bishop(Color color) {
		super(color);
	}

	@Override
	public Boolean checkMove(ChessMove mv, ChessBoard board) {
        
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
		if(!board.noPiece(to) && board.samePlayer(from, to))
            return false;
        
        if(Math.abs(from.getX() - to.getX() ) != Math.abs(from.getY() - to.getY()))
            return false;
        
        if(!board.freeRoad(mv))
            return false;
        
        return true;
	}

}
