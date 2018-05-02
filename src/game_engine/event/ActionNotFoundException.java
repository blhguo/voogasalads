package game_engine.event;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Andy Nguyen, Ben Hubsch
 * The purpose of this class is to provide a way to throw an exception in the case where Authoring
 * tries to create an incorrect/invalid Action that doesn't exist within the game engine.
 *
 */
public class ActionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create an exception based on an issue in our code.
	 */
	public ActionNotFoundException(String message) {
		super(String.format(message));
	}

	/**
	 * Create an exception based on an issue in our code.
	 */
	public ActionNotFoundException(String message, Object... values) {
		super(String.format(message, values));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public ActionNotFoundException(Throwable cause, String message, Object... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional message.
	 */
	public ActionNotFoundException(Throwable exception) {
		super(exception);
	}

}
