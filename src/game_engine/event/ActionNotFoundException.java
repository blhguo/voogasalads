package game_engine.event;

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
