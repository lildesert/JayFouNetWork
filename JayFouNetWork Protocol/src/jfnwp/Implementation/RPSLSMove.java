package jfnwp.Implementation;

import jfnwp.Interfaces.IMove;

public class RPSLSMove implements IMove {
	
	private EnumRPSLS value;

	public RPSLSMove(EnumRPSLS value) {
		this.value = value;
	}

	public EnumRPSLS getValue() {
		return value;
	}

	public void setValue(EnumRPSLS value) {
		this.value = value;
	}
}
