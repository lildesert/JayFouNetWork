package jfnwp.ChessImplementation;

import jfnwp.Implementation.Position;
import jfnwp.Interfaces.Color;

/**
 * @see ChessPiece
 * @version 1.0
 */
public class Pawn extends ChessPiece {
	
	private int mvt1Case;
	private int startingLine;
	
	public Pawn(Color color) {
		super(color);
		if(color == Color.White) {
            this.mvt1Case = -1;
            this.startingLine = 6;
        } else {
            this.mvt1Case = 1;
            this.startingLine = 1;
        }
	}

	public Boolean checkMove(ChessMove mv, ChessGame board) {
		
		Position from = mv.getFrom();
		Position to = mv.getTo();
		
		if(board.freeRoad(mv) && (board.noPiece(to) || ! board.samePlayer(from, to)) ) {

            if (Math.abs(from.getX() - to.getX()) == 1 )
                if(to.getY() - from.getY() == this.mvt1Case || (to.getY() == this.startingLine && to.getY() - from.getY() == this.mvt1Case*2) )
                    if (!board.noPiece(to) && !board.samePlayer(from, to))
                        return true;

            if ((from.getX() - to.getX()) == 0) {
                if ((to.getY() - from.getY()) == this.mvt1Case && board.noPiece(to)) {
                    return true;
                } else if (from.getY() == this.startingLine && (to.getY() - from.getY()) == this.mvt1Case * 2 && board.noPiece(to)) {
                    return true;
                }
            }
        }
        return false;
	}

}
