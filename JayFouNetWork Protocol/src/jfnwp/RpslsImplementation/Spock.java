package jfnwp.RpslsImplementation;

public class Spock extends RpslsComponent {

	@Override
	public int compareTo(RpslsComponent o) {
			switch (o.getClass().getSimpleName()) {
			case "Rock":
				return 1;
			case "Scissor":
				return 1;
			case "Paper":
				return 0;
			case "Lizard":
				return 0;
			case "Spock":
				return 2;
			default:
				return -1;
		}
	}
}
