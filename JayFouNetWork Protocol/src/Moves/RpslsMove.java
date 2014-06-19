package Moves;

import jfnwp.Implementation.Move;
import jfnwp.RpslsImplementation.*;

public class RpslsMove extends Move {
	
	private RpslsComponent c;

	public RpslsComponent getC() {
		return c;
	}

	@Override
	public void setData(String d) {
		switch(d)
		{
		case "Rock" :
			c = new Rock();
		case "Paper" :
			c = new Paper();
		case "Scissor" :
			c = new Scissor();
		case "Lizard" :
			c = new Lizard();
		case "Spock" :
			c = new Spock();
		}
	}
}
