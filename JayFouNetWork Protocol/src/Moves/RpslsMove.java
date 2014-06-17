package Moves;

import jfnwp.Interfaces.IMove;
import jfnwp.RpslsImplementation.*;

public class RpslsMove implements IMove {
	
	RpslsComponent c;

	public RpslsMove(String data)
	{
		switch(data)
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
	
	public RpslsComponent getC() {
		return c;
	}
}
