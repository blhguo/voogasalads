package gameData;

public class LoadDataException extends Exception {

	/**
	 * Author: Brandon
	 * This class represents an error thrown while loading data
	 */
	private static final long serialVersionUID = 6157950047229343615L;
	
	public LoadDataException(String message) {
		super(String.format(message));
	}
	
	public LoadDataException(Throwable exception) {
		super(exception);
	}

}
