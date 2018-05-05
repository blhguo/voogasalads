package gameData;

public class LoadDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6157950047229343615L;
	
	public LoadDataException(String message) {
		super(String.format(message));
	}
	
	public LoadDataException(Throwable exception) {
		super(exception);
	}

}
