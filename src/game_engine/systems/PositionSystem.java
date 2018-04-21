package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Level;
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
	private static final Class<? extends Component<Double>> X_POSITION = XPosComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	private static final Class<? extends Component<Double>> Y_POSITION = YPosComponent.class;
	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;

	/**
	 * Given the elapsed time within the current iteration of the game loop, this method gets all entities that contain the 
	 * necessary components and updates each of their Position component's attributes to reflect movement described in the
	 * velocities of their respective XPhysics and YPhysics components.
	 */
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(X_POSITION, X_VEL, Y_POSITION, Y_VEL);
		for (Entity e : level.getEntitiesContaining(args)) {
			Component<Double> xPos = e.getComponent(XPosComponent.class);
			Component<Double> yPos = e.getComponent(Y_POSITION);
			double xVel = e.getComponent(X_VEL).getValue();
			double yVel = e.getComponent(Y_VEL).getValue();
			
			xPos.setValue(xPos.getValue() + xVel * elapsedTime);
			yPos.setValue(yPos.getValue() + yVel * elapsedTime);
		}
	}
}
