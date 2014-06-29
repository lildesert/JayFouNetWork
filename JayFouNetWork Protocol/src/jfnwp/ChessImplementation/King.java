package jfnwp.ChessImplementation;

import jfnwp.Chess.Color;
import jfnwp.Chess.Position;
import jfnwp.Games.Chess;
import jfnwp.Moves.ChessMove;

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
	
	/**
	 * Check if a movement is valid
	 * @version 1.0
	 */
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
