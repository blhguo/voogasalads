package game_engine.components.keyboard;

import java.util.List;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;


/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * Component that stores the KeyCodes for left and right movement of an entity
 */
public class KeyboardMovementInputComponent implements Component {
	private static final Vector LEFT = new Vector(-1,0);
	private static final Vector RIGHT = new Vector(1,0);
	
	private KeyCode myLeft;
	private KeyCode myRight;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public KeyboardMovementInputComponent(List<String> args){
		myLeft = KeyCode.valueOf(args.get(0));
		myRight = KeyCode.valueOf(args.get(1));
	}
	
	public void setLeft(KeyCode left){
		myLeft = left;
	}
	
	public void setRight(KeyCode right){
		myRight = right;
	}
	
	public KeyCode getLeft(){
		return myLeft;
	}
	
	public KeyCode getRight(){
		return myRight;
	}
	
	public Vector getDirection(KeyCode dir){
		if(dir.equals(myLeft)){
			return LEFT;
		}else if(dir.equals(myRight)){
			return RIGHT;
		}else{
			return new Vector(0,0);
		}
	}
	
	public String getValues() {
		String vals = "Left,s," + getLeft().getName() + ";Right,s," + getRight().getName();
        return vals;
	}
	
	public String getName() {
		return "KeyboardMovementInput";
	}
}
