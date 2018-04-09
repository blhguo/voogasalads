package game_engine.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class KeyboardMoveDownInputComponent implements Component{
	private static final Vector DOWN = new Vector(0,-1);
	
	private KeyCode myDown;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param up
	 */
	public KeyboardMoveDownInputComponent(List<String> args){
		myDown = KeyCode.valueOf(args.get(0));
	}
	
	public void setDown(KeyCode down){
		myDown = down;
	}
	
	public boolean correctKey(KeyCode key){
		return myDown.equals(key);
	}
	
	public Vector getDirection(){
		return DOWN;
	}
}
