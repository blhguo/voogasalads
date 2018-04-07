package game_engine.components;

import game_engine.Component;

/**
 * @author: Jeremy Chen & Ben Hubsch
 *
 * Component that contains three fields important for collision logic:
 *
 *
 */
public class CollidableComponent implements Component{

	private boolean myIntersectable;
	private boolean myPassable;
	private int myPushable;

	private boolean collided;
	private ECollisionSide collisionSide;
	
	public CollidableComponent(boolean intersectable, boolean passable, int pushable){
		myIntersectable = intersectable;
		myPassable = passable;
		myPushable = pushable;

		collided = false;
	}
	
	public boolean getPassable(){
		return myPassable;
	}
	
	public void setPassible(boolean passable){
		myPassable = passable;
	}
}
