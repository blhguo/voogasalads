package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * 
 * This system class acts on all Entities that contain the Position, XPhysics, and YPhysics components. 
 * During each iteration of the game loop, this system will move these entities based on the data that is
 * stored in these respective components.
 *
 */
public class PositionSystem extends GameSystem {
	private static final Class<? extends Component> X_POSITION = XPosComponent.class;
	private static final Class<? extends Component> X_VEL = XVelComponent.class;
	private static final Class<? extends Component> Y_POSITION = YPosComponent.class;
	private static final Class<? extends Component> Y_VEL = YVelComponent.class;

	/**
	 * Creates a new instance of the MovementSystem class
	 * @param engine
	 */
	public PositionSystem(Engine engine) {
		super(engine);
	}

	/**
	 * Given the elapsed time within the current iteration of the game loop, this method gets all entities that contain the 
	 * necessary components and updates each of their Position component's attributes to reflect movement described in the
	 * velocities of their respective XPhysics and YPhysics components.
	 */
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(X_POSITION, X_VEL, Y_POSITION, Y_VEL);
		for (Entity e : getEngine().getEntitiesContaining(args)) {
			Component xPos = e.getComponent(X_POSITION);
			Component yPos = e.getComponent(Y_POSITION);
			double xVel = Double.parseDouble(e.getComponent(X_VEL).getValue());
			double yVel = Double.parseDouble(e.getComponent(Y_VEL).getValue());

			xPos.setValue(Double.toString(Double.parseDouble(xPos.getValue()) + xVel * elapsedTime));
			yPos.setValue(Double.toString(Double.parseDouble(yPos.getValue()) + yVel * elapsedTime));
		}
	}
}
