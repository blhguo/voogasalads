package game_engine.components.keyboard;

import java.util.List;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class LeftKeyboardMovementInputComponent implements Component{
	private static final Vector LEFT = new Vector(-1,0);
	
	private KeyCode myLeft;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public LeftKeyboardMovementInputComponent(String value){
		myLeft = KeyCode.valueOf(value);
	}

	
	@Override
	public String getValue() {
		return myLeft.getName();
	}


	@Override
	public void setValue(String value) {
		myLeft = KeyCode.valueOf(value);
	}
	
}
