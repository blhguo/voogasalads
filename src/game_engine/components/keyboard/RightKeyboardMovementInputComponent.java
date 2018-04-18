package game_engine.components.keyboard;

import java.util.List;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class RightKeyboardMovementInputComponent implements Component{
	
	private static final Vector RIGHT = new Vector(1,0);
	
	private KeyCode myRight;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public RightKeyboardMovementInputComponent(String value){
		myRight = KeyCode.valueOf(value);
	}

	
	@Override
	public String getValue() {
		return myRight.getName();
	}


	@Override
	public void setValue(String value) {
		myRight = KeyCode.valueOf(value);
	}

}
