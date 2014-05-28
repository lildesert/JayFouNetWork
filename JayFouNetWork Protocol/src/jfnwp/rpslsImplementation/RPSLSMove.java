package jfnwp.rpslsImplementation;

import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.Piece;
import jfnwp.chessImplementation.ChessPiece;

/**
 * @version 1.0
 */
public class RPSLSMove implements IMove {
	
	private EnumRPSLS m;

	public RPSLSMove(EnumRPSLS m) {
		super();
		this.m = m;
	}

	public EnumRPSLS getType() {
		return m;
	}

	public void setType(EnumRPSLS m) {
		this.m = m;
	}
}
