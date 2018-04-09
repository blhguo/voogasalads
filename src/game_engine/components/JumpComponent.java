package game_engine.components;

import game_engine.Component;
import javafx.scene.input.KeyCode;

public class JumpComponent implements Component{
	private boolean myOnGround;
	private int myJumpsAllowed;
	public JumpComponent(boolean onGround, int jumpsAllowed){
		myOnGround = onGround;
		myJumpsAllowed = jumpsAllowed;
	}
	public void setOnGround(boolean onGround){
		myOnGround = onGround;
	}
	
	public void setJumpsAllowed(int jumpsAllowed){
		myJumpsAllowed = jumpsAllowed;
	}
	public boolean getOnGround(){
		return myOnGround;
	}
	
	public int getJumpsAllowed(){
		return myJumpsAllowed;
	}
}
