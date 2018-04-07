package game_engine.components;



import java.util.HashMap;
import java.util.Map;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class KeyboardMovementInput implements Component{
	private static final Vector LEFT = new Vector(-1,0);
	private static final Vector RIGHT = new Vector(1,0);
	private static final Vector UP = new Vector(0,1);
	private static final Vector DOWN = new Vector(0,1);
	
	private Map<KeyCode, Vector> myDirections;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public KeyboardMovementInput(KeyCode left, KeyCode right, KeyCode up, KeyCode down){
		myDirections = new HashMap<>();
		myDirections.put(left, LEFT);
		myDirections.put(right, RIGHT);
		myDirections.put(up, UP);
		myDirections.put(down, DOWN);
	}
	
	public void setLeft(KeyCode left){
		myDirections.put(left, LEFT);
	}
	
	public void setRight(KeyCode right){
		myDirections.put(right, RIGHT);
	}
	
	public void setUp(KeyCode up){
		myDirections.put(up, UP);
	}
	
	public void setDown(KeyCode down){
		myDirections.put(down, DOWN);
	}
	
	public Vector getDirection(KeyCode dir){
		if(myDirections.containsKey(dir)){
			return new Vector(0,0);
		}
		return myDirections.get(dir);
	}
}
