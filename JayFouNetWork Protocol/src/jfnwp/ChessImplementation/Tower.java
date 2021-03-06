package jfnwp.ChessImplementation;

import jfnwp.Chess.Color;
import jfnwp.Chess.Piece;
import jfnwp.Chess.Position;
import jfnwp.Games.Chess;
import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.IPosition;
import jfnwp.Moves.ChessMove;

/**
 * @see ChessPiece
 * @version 1.0
 */
public class Tower extends ChessPiece {
	
	private Boolean firstMouv;
	
	public Tower(Color color) {
		super(color);
		firstMouv = true;
	}
	
	/**
	 * Check if a movement is valid
	 * @version 1.0
	 */
	public Boolean checkMove(ChessMove mv, Chess board) {
        
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
		if(mv.noMove())
			return false;
		
		if(from.getX() != to.getX() && from.getY() != to.getY())
			return false;
		
        if(!board.noPiece(to) && board.samePlayer(from, to))
            return false;
        
        if(!board.freeRoad(mv))
            return false;
        
        return true;
    }
}
