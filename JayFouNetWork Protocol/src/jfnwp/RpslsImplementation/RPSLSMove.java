package jfnwp.RpslsImplementation;

import jfnwp.ChessImplementation.ChessPiece;
import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.Piece;

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
