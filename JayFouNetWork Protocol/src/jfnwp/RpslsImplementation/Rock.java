package jfnwp.RpslsImplementation;

public class Rock extends RpslsComponent {

	@Override
	public int compareTo(RpslsComponent o) {
			switch (o.getClass().getSimpleName()) {
			case "Rock":
				return 2;
			case "Scissor":
				return 1;
			case "Paper":
				return 0;
			case "Lizard":
				return 1;
			case "Spock":
				return 0;
			default:
				return -1;
		}
	}

}
