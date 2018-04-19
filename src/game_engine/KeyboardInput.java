package game_engine;

import javafx.scene.input.KeyCode;

/**
 * 
 * @author Andy Nguyen
 * 
 * The Class KeyboardInput.
 */
public class KeyboardInput implements Input {
	private String myInput;

	/**
	 * Instantiates a new KeyboardInput object.
	 *
	 * @param key the key
	 */
	public KeyboardInput(KeyCode key) {
		myInput = key.toString();
	}

	/* (non-Javadoc)
	 * @see game_engine.Input#getInput()
	 */
	public String getInput() {
		return myInput;
	}
}
