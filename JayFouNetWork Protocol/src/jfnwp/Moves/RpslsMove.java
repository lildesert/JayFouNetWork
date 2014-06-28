package jfnwp.Moves;

import jfnwp.Implementation.Move;
import jfnwp.Rpsls.*;

/**
 * @see Move
 * @version 1.0
 */
public class RpslsMove extends Move {
	
	private RpslsComponent c;

	public RpslsComponent getC() {
		return c;
	}

	public void deserialize(String d) {
		switch(d)
		{
		case "Rock" :
			c = new Rock();
			break;
		case "Paper" :
			c = new Paper();
			break;
		case "Scissor" :
			c = new Scissor();
			break;
		case "Lizard" :
			c = new Lizard();
			break;
		case "Spock" :
			c = new Spock();
			break;
		}
	}
}
