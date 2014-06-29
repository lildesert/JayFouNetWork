package jfnwp.Exception;

/**
 * Exception used when the server is full.
 * @version 1.0
 */
public class GameFullException extends Exception {

	public GameFullException()
	{ }  
	
	public GameFullException(String message) {  
		super(message); 
	}  
}
