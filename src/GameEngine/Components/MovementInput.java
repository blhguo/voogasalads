package GameEngine.Components;

import GameEngine.Component;
import javafx.scene.input.KeyCode;

public class MovementInput implements Component{
	private KeyCode myLeftKey;
	private KeyCode myRightKey;
	private KeyCode myUpKey;
	private KeyCode myDownKey;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public MovementInput(KeyCode left, KeyCode right, KeyCode up, KeyCode down){
		myLeftKey = left;
		myRightKey = right;
		myUpKey = up;
		myDownKey = down;
	}
	
	public KeyCode getLeft(){
		return myLeftKey;
	}
	
	public KeyCode getRight(){
		return myRightKey;
	}
	
	public KeyCode getUp(){
		return myUpKey;
	}
	
	public KeyCode getDown(){
		return myDownKey;
	}
}
