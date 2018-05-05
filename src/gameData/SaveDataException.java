package gameData;

public class SaveDataException extends Exception {

	/**
	 * This class represents the errors thrown while saving data, indicating IOExceptions, or non-existent files, among other thigns
	 */
	private static final long serialVersionUID = 6157950047229343615L;
	
	public SaveDataException(String message) {
		super(String.format(message));
	}
	
	public SaveDataException(Throwable exception) {
		super(exception);
	}

}
