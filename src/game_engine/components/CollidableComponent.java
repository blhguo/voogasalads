package game_engine.components;

import game_engine.Component;

/**
 * @author: Jeremy Chen
 *
 * Component that contains three fields important for collision logic:
 *
 *
 */
public class CollidableComponent implements Component{

	private boolean myIntersectable;
	private boolean myPassable;
	private int myPushable;
	
	public CollidableComponent(boolean intersectable, boolean passable, int pushable){
		myIntersectable = intersectable;
		myPassable = passable;
		myPushable = pushable;
	}
	
	public boolean getPassable(){
		return myPassable;
	}
	
	public void setPassible(boolean passable){
		myPassable = passable;
	}
	
	public boolean getIntersectable(){
		return myIntersectable;
	}
	
	public void setIntersectable(boolean intersectable){
		myIntersectable = intersectable;
	}
	
	public int getPushable(){
		return myPushable;
	}
	
	public void setPushable(int pushable){
		myPushable = pushable;
	}
}
