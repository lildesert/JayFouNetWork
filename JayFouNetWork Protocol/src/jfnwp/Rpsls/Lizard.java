package jfnwp.Rpsls;

import jfnwp.Implementation.Move;

/**
 * @see RpslsComponent
 * @version 1.0
 */
public class Lizard extends RpslsComponent {

	@Override
	public int compareTo(RpslsComponent o) {
			switch (o.getClass().getSimpleName()) {
			case "Rock":
				return 0;
			case "Scissor":
				return 0;
			case "Paper":
				return 1;
			case "Lizard":
				return 2;
			case "Spock":
				return 1;
			default:
				return -1;
		}
	}
}
