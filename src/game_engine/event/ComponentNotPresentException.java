package game_engine.event;

import java.util.List;

import game_engine.Component;

public class ComponentNotPresentException extends RuntimeException {

	private static final long serialVersionUID = -8458360485433191741L;

	/**
	 * Create an exception based on an issue in our code.
	 */
	public ComponentNotPresentException(String message, Object... values) {
		super(String.format(message, values));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public ComponentNotPresentException(Throwable cause, String message, Object... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional message.
	 */
	public ComponentNotPresentException(Throwable exception) {
		super(exception);
	}

	public ComponentNotPresentException(List<Class<? extends Component<?>>> list) {
		super(String.format("%s not all present.", list));
	}
}