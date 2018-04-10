package game_engine.components.keyboard;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class KeyboardMovementInputComponent implements Component{
	private static final Vector LEFT = new Vector(-1,0);
	private static final Vector RIGHT = new Vector(1,0);
	
	private Map<KeyCode, Vector> myDirections;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public KeyboardMovementInputComponent(List<String> args){
		myDirections = new HashMap<>();
		myDirections.put(KeyCode.valueOf(args.get(0)), LEFT);
		myDirections.put(KeyCode.valueOf(args.get(1)), RIGHT);
	}
	
	public void setLeft(KeyCode left){
		myDirections.put(left, LEFT);
	}
	
	public void setRight(KeyCode right){
		myDirections.put(right, RIGHT);
	}
	
	public Vector getDirection(KeyCode dir){
		if(!myDirections.containsKey(dir)){
			return new Vector(0,0);
		}
		return myDirections.get(dir);
	}
}
