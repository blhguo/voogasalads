package game_engine.components;

import java.util.List;

import game_engine.Component;

public class JumpComponent implements Component{
	private boolean myOnGround;
	private int myJumpsAllowed;
	private int myDefaultJumpsAllowed;
	
	public JumpComponent(List<String> args){
		myOnGround = Boolean.parseBoolean(args.get(0));
		myJumpsAllowed = Integer.parseInt(args.get(1));
		myDefaultJumpsAllowed = myJumpsAllowed;
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
	
	public int getDefaultJumpsAllowed(){
		return myDefaultJumpsAllowed;
	}
}
