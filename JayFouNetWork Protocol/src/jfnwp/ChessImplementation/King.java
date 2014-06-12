package jfnwp.ChessImplementation;

import jfnwp.Games.Chess;
import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;

/**
 * @see ChessPiece
 * @version 1.0
 */
public class King extends ChessPiece {
	
	private Boolean firstMouv;
	
	public King(Color color) {
		super(color);
		firstMouv = true;
	}
	
	@Override
	public Boolean checkMove(ChessMove mv, Chess board) {
		
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
        if (!board.noPiece(to) && board.samePlayer(from, to))
            return false;

        if (Math.abs(to.getX() - from.getX()) > 1 || Math.abs(to.getY() - from.getY()) > 1)
            return false;
        
        if(board.becomeMat(mv))
            return false;
        
        return true;
	}
}
