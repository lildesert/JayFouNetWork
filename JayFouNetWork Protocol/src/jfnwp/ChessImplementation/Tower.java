package jfnwp.ChessImplementation;

import jfnwp.Games.Chess;
import jfnwp.Implementation.Position;
import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.Color;
import jfnwp.Interfaces.IPosition;
import jfnwp.Interfaces.Piece;
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

	public Boolean checkMove(ChessMove mv, Chess board) {
        
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
		if(mv.noMove())
			return false;
		
        if(!board.noPiece(to) && board.samePlayer(from, to))
            return false;
        
        if(!board.freeRoad(mv))
            return false;
        
        return true;
    }
}
