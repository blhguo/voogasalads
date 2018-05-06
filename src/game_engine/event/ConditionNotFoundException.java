package game_engine.event;

public class ConditionNotFoundException extends RuntimeException{

	/**
	 * The purpose of this class is to throw an exception when a condition is not found
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create an exception based on an issue in our code.
	 */
	public ConditionNotFoundException(String message) {
		super(String.format(message));
	}

	/**
	 * Create an exception based on an issue in our code.
	 */
	public ConditionNotFoundException(String message, Object... values) {
		super(String.format(message, values));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public ConditionNotFoundException(Throwable cause, String message, Object... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional message.
	 */
	public ConditionNotFoundException(Throwable exception) {
		super(exception);
	}

}
