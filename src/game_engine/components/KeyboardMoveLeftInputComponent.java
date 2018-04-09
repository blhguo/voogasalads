package game_engine.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class KeyboardMoveLeftInputComponent implements Component{
	private static final Vector LEFT = new Vector(-1,0);
	
	private KeyCode myLeft;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 */
	public KeyboardMoveLeftInputComponent(List<String> args){
		myLeft = KeyCode.valueOf(args.get(0));
	}
	
	public void setLeft(KeyCode left){
		myLeft = left;
	}
	
	public boolean correctKey(KeyCode key){
		return myLeft.equals(key);
	}
	
	public Vector getDirection(){
		return LEFT;
	}
}
