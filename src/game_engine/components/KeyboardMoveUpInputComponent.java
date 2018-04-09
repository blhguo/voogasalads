package game_engine.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class KeyboardMoveUpInputComponent implements Component{
	private static final Vector UP = new Vector(0,1);
	
	private KeyCode myUp;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param up
	 */
	public KeyboardMoveUpInputComponent(List<String> args){
		myUp = KeyCode.valueOf(args.get(0));
	}
	
	public void setRight(KeyCode up){
		myUp = up;
	}
	
	public boolean correctKey(KeyCode key){
		return myUp.equals(key);
	}
	
	public Vector getDirection(){
		return UP;
	}
}