package game_engine.components;

import game_engine.Component;

// I think not needed
public class KeyboardInput implements Component {
	private String myLeft;
	private String myRight;
	private String myUp;
	private String myDown;
	
	public KeyboardInput(String left, String right, String up, String down) {
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
