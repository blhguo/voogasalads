package game_engine;

/**
 * 
 * @author benhubsch
 * 
 * This error is thrown from within the ComponentFactory.
 *
 */
public class ComponentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 408230963684365816L;

	/**
	 * Create an exception based on an issue in our code.
	 */
	public ComponentNotFoundException(String message) {
		super(String.format(message));
	}

	/**
	 * Create an exception based on an issue in our code.
	 */
	public ComponentNotFoundException(String message, Object... values) {
		super(String.format(message, values));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public ComponentNotFoundException(Throwable cause, String message, Object... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional message.
	 */
	public ComponentNotFoundException(Throwable exception) {
		super(exception);
	}
}