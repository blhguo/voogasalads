package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.PositionComponent;
import game_engine.components.physics.XPhysicsComponent;
import game_engine.components.physics.YPhysicsComponent;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * 
 * This system class acts on all Entities that contain the Position, XPhysics, and YPhysics components. 
 * During each iteration of the game loop, this system will move these entities based on the data that is
 * stored in these respective components.
 *
 */
public class MovementSystem extends GameSystem {
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	private static final Class<? extends Component> HORIZONTAL_PHYSICS = XPhysicsComponent.class;
	private static final Class<? extends Component> VERTICAL_PHYSICS = YPhysicsComponent.class;

	/**
	 * Creates a new instance of the MovementSystem class
	 * @param engine
	 */
	public MovementSystem(Engine engine) {
		super(engine);
	}

	
	// https://gamedev.stackexchange.com/questions/29617/how-to-make-a-character-jump
	// http://jsfiddle.net/LyM87/3267/
	/**
	 * Given the elapsed time within the current iteration of the game loop, this method gets all entities that contain the 
	 * necessary components and updates each of their Position component's attributes to reflect movement described in the
	 * velocities of their respective XPhysics and YPhysics components.
	 */
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(VERTICAL_PHYSICS, HORIZONTAL_PHYSICS, POSITION);
		for (Entity e : getEngine().getEntitiesContaining(args)) {
			XPhysicsComponent xPhysics = (XPhysicsComponent) e.getComponent(HORIZONTAL_PHYSICS);
			YPhysicsComponent yPhysics = (YPhysicsComponent) e.getComponent(VERTICAL_PHYSICS);
			PositionComponent position = (PositionComponent) e.getComponent(POSITION);
			
			position.setX(position.getX() + xPhysics.getCurrVel() * elapsedTime);
			position.setY(position.getY() + yPhysics.getCurrVel() * elapsedTime);
			

			
			/**
			 *  TODO bad hotfix need to update
			 *  
			 */
			yPhysics.setCurrVel(yPhysics.getCurrVel() + -1 * yPhysics.getAccel() * elapsedTime);
			xPhysics.setCurrVel(xPhysics.getCurrVel() + -1 * xPhysics.getAccel() * elapsedTime);
		}
	}
}
