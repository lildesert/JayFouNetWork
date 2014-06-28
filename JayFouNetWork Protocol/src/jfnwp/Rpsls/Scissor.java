package jfnwp.Rpsls;

/**
 * @see RpslsComponent
 * @version 1.0
 */
public class Scissor extends RpslsComponent{

	@Override
	public int compareTo(RpslsComponent o) {
			switch (o.getClass().getSimpleName()) {
			case "Rock":
				return 0;
			case "Paper":
				return 1;
			case "Scissor":
				return 2;
			case "Lizard":
				return 1;
			case "Spock":
				return 0;
			default:
				return -1;
		}
	}
}
