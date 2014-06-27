package jfnwp.ChessImplementation;

import jfnwp.Chess.Color;
import jfnwp.Chess.Position;
import jfnwp.Games.Chess;
import jfnwp.Moves.ChessMove;

/**
 * @see ChessPiece
 * @version 1.0
 */
public class Queen extends ChessPiece {

	public Queen(Color color) {
		super(color);
	}

	@Override
	public Boolean checkMove(ChessMove mv, Chess board) {
		
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
        if(!board.noPiece(to) && board.samePlayer(from, to))
            return false;
        
        if((to.getX() != from.getX()) && (to.getY() != from.getY()))
            if( ( Math.abs(from.getX() - to.getX() )) != Math.abs(( from.getY() - to.getY() )) )
                return false;
        
        if(!board.freeRoad(mv))
            return false;
        
        return true;
	}

}
