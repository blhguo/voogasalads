package game_engine.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class KeyboardMoveRightInputComponent implements Component{
	private static final Vector RIGHT = new Vector(1,0);
	
	private KeyCode myRight;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 */
	public KeyboardMoveRightInputComponent(List<String> args){
		myRight = KeyCode.valueOf(args.get(0));
	}
	
	public void setRight(KeyCode left){
		myRight = left;
	}
	
	public boolean correctKey(KeyCode key){
		return myRight.equals(key);
	}
	
	public Vector getDirection(){
		return RIGHT;
	}
}