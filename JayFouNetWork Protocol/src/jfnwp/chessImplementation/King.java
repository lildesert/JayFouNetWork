package jfnwp.chessImplementation;

import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;

public class King extends ChessPiece {
	
	private Boolean firstMouv;
	
	public King(Color color) {
		super(color);
		firstMouv = true;
	}
	
	@Override
	public Boolean checkMove(ChessMove mv, ChessBoard board) {
		
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
        if (!board.noPiece(to) && board.samePlayer(from, to))
            return false;

        if (Math.abs(to.getX() - from.getX()) > 1 || Math.abs(to.getY() - from.getY()) > 1)
            return false;
        
        int y;
        if (this.color == Color.White) {
            y = 7;
        } else {
            y = 0;
        }
        
        if(board.seMetEnEchec(mv))
            return false;
        
        return true;
	}
}
