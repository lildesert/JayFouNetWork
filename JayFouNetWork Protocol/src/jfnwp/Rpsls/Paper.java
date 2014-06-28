package jfnwp.Rpsls;

/**
 * @see RpslsComponent
 * @version 1.0
 */
public class Paper extends RpslsComponent {

	@Override
	public int compareTo(RpslsComponent o) {
			switch (o.getClass().getSimpleName()) {
			case "Rock":
				return 1;
			case "Paper":
				return 2;
			case "Scissor":
				return 0;
			case "Lizard":
				return 0;
			case "Spock":
				return 1;
			default:
				return -1;
		}
	}
}
