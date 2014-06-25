package jfnwp.Moves;

import jfnwp.Implementation.Move;
import jfnwp.RpslsImplementation.*;

public class RpslsMove extends Move {
	
	private RpslsComponent c;
	private String serialMv;

	public RpslsComponent getC() {
		return c;
	}

	public void deserialize(String d) {
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

	@Override
	public void serialize(String s) {
		serialMv = s;
	}
	
	public String getSerializedData()
	{
		return serialMv;
	}
}
