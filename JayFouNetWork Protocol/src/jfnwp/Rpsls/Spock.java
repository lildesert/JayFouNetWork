package jfnwp.Rpsls;

/**
 * @see RpslsComponent
 * @version 1.0
 */
public class Spock extends RpslsComponent {
	
	/**
	 * Get the winner
	 * @param RpslsComponent
	 */
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
