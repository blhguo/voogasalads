package game_engine.components.keyboard;

import java.util.List;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class KeyboardJumpInputComponent implements Component{
	private static final Vector UP = new Vector(0,1);
	private KeyCode myUp;
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public KeyboardJumpInputComponent(List<String> args){
		myUp = KeyCode.valueOf(args.get(0));
	}
	
	public void setUp(KeyCode up){
		myUp = up;
	}
	
	public Vector getDirection(KeyCode up){
		if(up.compareTo(myUp) != 0){
			return new Vector(0,0);
		}
		//This is a test for mergine
		return UP;
	}

	@Override
	public String getValues() {
		return null;
	}

	@Override
	public String getName() {
		return "Keyboard Jump Input";
	}
}
