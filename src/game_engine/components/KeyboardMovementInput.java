package game_engine.components;



import java.util.HashMap;
import java.util.Map;

import game_engine.Component;
import game_engine.Vector;

public class KeyboardMovementInput implements Component{
	private static final Vector LEFT = new Vector(-1,0);
	private static final Vector RIGHT = new Vector(1,0);
	private static final Vector UP = new Vector(0,1);
	private static final Vector DOWN = new Vector(0,1);
	private double myMovementOffset;
	
	private Map<String, Vector> myDirections;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public KeyboardMovementInput(String left, String right, String up, String down, double offset){
		myDirections = new HashMap<>();
		myDirections.put(left, LEFT);
		myDirections.put(right, RIGHT);
		myDirections.put(up, UP);
		myDirections.put(down, DOWN);
		myMovementOffset = offset;
	}
	
	public void setLeft(String left){
		myDirections.put(left, LEFT);
	}
	
	public void setRight(String right){
		myDirections.put(right, RIGHT);
	}
	
	public void setUp(String up){
		myDirections.put(up, UP);
	}
	
	public void setDown(String down){
		myDirections.put(down, DOWN);
	}
	
	public Vector getDirection(String dir){
		if(myDirections.containsKey(dir)){
			return new Vector(0,0);
		}
		return myDirections.get(dir);
	}
	
	public double getOffset(){
		return myMovementOffset;
	}
}
