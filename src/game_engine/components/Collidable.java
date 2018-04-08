package game_engine.components;

import game_engine.Component;

public class Collidable implements Component{
	
	private boolean myPassable;
	
	public Collidable(boolean passable){
		myPassable = passable;
	}
	
	public boolean getPassable(){
		return myPassable;
	}
	
	public void setPassible(boolean passable){
		myPassable = passable;
	}
}
