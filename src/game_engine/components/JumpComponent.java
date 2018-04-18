package game_engine.components;

import java.util.List;

import game_engine.Component;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * 
 * This component defines whether or not an entity can jump as well as the number of jumps allowed by the entity.
 */

public class JumpComponent implements Component{
	private boolean myOnGround;
	private int myJumpsAllowed;
	private int myDefaultJumpsAllowed;
	
	public JumpComponent(List<String> args){
		myJumpsAllowed = Integer.parseInt(args.get(0));
		myOnGround = true;
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

	@Override
	public String getValues() {
		String vals = "Jumps allowed,d," + myJumpsAllowed;
        return vals;
	}
	@Override
	public String getName() {
		return "Jump";
	}
}
