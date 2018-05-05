package gameData;

public class SaveDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6157950047229343615L;
	
	public SaveDataException(String message) {
		super(String.format(message));
	}
	
	public SaveDataException(Throwable exception) {
		super(exception);
	}

}
