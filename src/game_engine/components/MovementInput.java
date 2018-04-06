package game_engine.components;



import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class MovementInput implements Component{
	private Vector myLeft;
	private Vector myRight;
	private Vector myUp;
	private Vector myDown;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public MovementInput(Vector left, Vector right, Vector up, Vector down){
		myLeft = left;
		myRight = right;
		myUp = up;
		myDown = down;
	}
	
	public Vector getLeft(){
		return myLeft;
	}
	
	public Vector getRight(){
		return myRight;
	}
	
	public Vector getUp(){
		return myUp;
	}
	
	public Vector getDown(){
		return myDown;
	}
}
