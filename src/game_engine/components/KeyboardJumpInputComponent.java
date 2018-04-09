package game_engine.components;

import java.util.List;

import game_engine.Component;
import game_engine.Vector;
import javafx.scene.input.KeyCode;

public class KeyboardJumpInputComponent implements Component{
	private static final Vector JUMP = new Vector(0,1);
	private KeyCode myJump;
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public KeyboardJumpInputComponent(List<String> args){
		myJump = KeyCode.valueOf(args.get(0));
	}
	
	public void setUp(KeyCode jump){
		myJump = jump;
	}
	
	public boolean correctKey(KeyCode key){
		return myJump.equals(key);
	}
	
	public Vector getDirection(){
		return JUMP;
	}
}
