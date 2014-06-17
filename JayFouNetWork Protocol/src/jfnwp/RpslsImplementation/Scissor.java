package jfnwp.RpslsImplementation;

public class Scissor extends RpslsComponent{

	@Override
	public int compareTo(RpslsComponent o) {
			switch (o.getClass().getSimpleName()) {
			case "Rock":
				return 0;
			case "Paper":
				return 1;
			case "Lizard":
				return 1;
			case "Spock":
				return 0;
			default:
				return -1;
		}
	}
}
