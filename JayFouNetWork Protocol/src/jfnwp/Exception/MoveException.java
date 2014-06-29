package jfnwp.Exception;

/**
 * Exception used when the move is incorrect
 * @see Move
 * @version 1.0
 */
public class MoveException extends Exception {
	
	public MoveException()
	{ }
	
	public MoveException(String message) {  
		super(message); 
	} 
}
