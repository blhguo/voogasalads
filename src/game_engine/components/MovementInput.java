package game_engine.components;

import game_engine.Component;

public class MovementInput implements Component {
	private String myLeft;
	private String myRight;
	private String myUp;
	private String myDown;
	
	/**
	 * Creates a new instance of MovementInput with specified key input codes
	 * @param left
	 * @param right
	 * @param up
	 * @param down
	 */
	public MovementInput(String left, String right, String up, String down) {
		myLeft = left;
		myRight = right;
		myUp = up;
		myDown = down;
	}
	
	public String getLeft() {
		return myLeft;
	}
	
	public String getRight() {
		return myRight;
	}
	
	public String getUp() {
		return myUp;
	}
	
	public String getDown() {
		return myDown;
	}
}
