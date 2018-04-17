package voogasalad.util.voogle_images.view.download;

/**
 * Exception thrown when a user attempts to open an image on their local machine but no application
 * can be found that supports that behavior.
 * 
 * @author benhubsch
 */
public class DesktopNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = -8458360485433191741L;

	/**
	 * Create an exception based on an issue in our code.
	 */
	public DesktopNotSupportedException(String message, Object... values) {
		super(String.format(message, values));
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	public DesktopNotSupportedException(Throwable cause, String message, Object... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional message.
	 */
	public DesktopNotSupportedException(Throwable exception) {
		super(exception);
	}
}
