package jfnwp.rpslsImplementation;

import jfnwp.Interfaces.IMove;
import jfnwp.Interfaces.Piece;

public class RPSLSMove implements IMove {
	
	private Piece p;

	public RPSLSMove(Piece p) {
		super();
		this.p = p;
	}

	public Piece getP() {
		return p;
	}

	public void setP(Piece p) {
		this.p = p;
	}
}
