package game_engine.components;

import java.util.List;

import game_engine.Component;

public class JumpComponent implements Component{
	private boolean myOnGround;
	private int myJumpsAllowed;
	private int myDefaultJumpsAllowed;
	private double myJumpVelocity;
	
	public JumpComponent(List<String> args){
		myOnGround = Boolean.parseBoolean(args.get(0));
		myJumpsAllowed = Integer.parseInt(args.get(1));
		myDefaultJumpsAllowed = myJumpsAllowed;
		myJumpVelocity = Double.parseDouble(args.get(2));
	}
	public void setOnGround(boolean onGround){
		myOnGround = onGround;
	}
	
	public void setJumpsAllowed(int jumpsAllowed){
		myJumpsAllowed = jumpsAllowed;
	}
	
	public void setJumpVelocity(double velocity){
		myJumpVelocity = velocity;
	}
	
	public boolean getOnGround(){
		return myOnGround;
	}
	
	public int getJumpsAllowed(){
		return myJumpsAllowed;
	}
	
	public double getJumpVelocity(){
		return myJumpVelocity;
	}
	
	public int getDefaultJumpsAllowed(){
		return myDefaultJumpsAllowed;
	}

	@Override
	public String getValues() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}
}
