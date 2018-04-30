package game_engine.components.collision;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * @author: Jeremy Chen
 *
 * Component that contains three fields important for collision logic:
 * Intersectable: Signals to systems that this Entity should have collision detection (currently not used)
 * Passable: Signals to systems that this Entity is "tangible" (Use case: if intersectable & not passable, other entities can pass through it, but collisions are still detected)
 * Pushable: An int, whose value dictates what Entities can be pusehd by this entity (higher can push lower)
 */
@DataConditionable
public class CollidableComponent extends Component<Boolean> {
	public CollidableComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}
}
	
//
//	private boolean myIntersectable;
//	private boolean myPassable;
//	private int myPushable;
//
//	/**
//	 * @param args
//	 *  Constructor allowing Authoring to pass in necessary args (specified in Resource file)
//	 */
//	public CollidableComponent(List<String> args){
////		System.out.println(args);
//		myIntersectable = Boolean.parseBoolean(args.get(0));
//		myPassable = Boolean.parseBoolean(args.get(1));
//		myPushable = Integer.parseInt(args.get(2));
//	}
//	
//	/**
//	 * @return
//	 * Getter for passable
//	 */
//	public boolean getPassable(){
//		return myPassable;
//	}
//	
//	/**
//	 * @param passable
//	 * Setter for passable
//	 */
//	public void setPassable(boolean passable){
//		myPassable = passable;
//	}
//	
//	/**
//	 * @return
//	 * Getter for intersectable
//	 */
//	public boolean getIntersectable(){
//		return myIntersectable;
//	}
//	
//	/**
//	 * @param intersectable
//	 * SEtter for intersectable
//	 */
//	public void setIntersectable(boolean intersectable){
//		myIntersectable = intersectable;
//	}
//	
//	/**
//	 * @return
//	 * Getter for Pushable
//	 */
//	public int getPushable(){
//		return myPushable;
//	}
//	
//	/**
//	 * @param pushable
//	 * SEtter for pushable
//	 */
//	public void setPushable(int pushable){
//		myPushable = pushable;
//	}
//
//	/* (non-Javadoc)
//	 * @see game_engine.Component#getValues()
//	 * Stopgap method that allows for editing of already instantiated components in Authoring
//	 */
//	@Override
//	public String getValues() {
//		String vals = "Collideable,b," + getIntersectable() + ";Passable,b," + getPassable() + ";Pushable,d," + getPushable();
////		Collidable=Collidable,b,true;Passable,b,true;Pushable,d,0
//		return vals;
//	}
//
//	/* (non-Javadoc)
//	 * @see game_engine.Component#getName()
//	 * Stopgabp method that allows for persistent labeling of components in Authoring
//	 */
//	@Override
//	public String getName() {
//		return "Collidable";
//	}
//}
