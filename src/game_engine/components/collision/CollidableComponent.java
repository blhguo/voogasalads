package game_engine.components.collision;

import game_engine.Component;

import java.util.List;

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

	public CollidableComponent(List<String> args){
		myIntersectable = Boolean.parseBoolean(args.get(0));
		myPassable = Boolean.parseBoolean(args.get(1));
		myPushable = (int) Double.parseDouble(args.get(2));
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

	public String getValues(){
		return "Intersectable,b," + getIntersectable() + ";Passable,d," + getPassable() + ";Pushable,d," + getPushable();
	}

	@Override
	public String getName() {
		return "Collidable";
	}
}
