package game_engine.systems.position;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.level.Level;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * 
 * This system class acts on all Entities that contain the Position, XPhysics, and YPhysics components. 
 * During each iteration of the game loop, this system will move these entities based on the data that is
 * stored in these respective components.
 *
 */
public abstract class PositionSystem implements GameSystem {
	
	private Class<? extends Component<Double>> myPosition;
	private Class<? extends Component<Double>> myVelocity;
	
	public PositionSystem(Class<? extends Component<Double>> position, Class<? extends Component<Double>> velocity) {
		myPosition = position;
		myVelocity = velocity;
	}

	/**
	 * Given the elapsed time within the current iteration of the game loop, this method gets all entities that contain the 
	 * necessary components and updates each of their Position component's attributes to reflect movement described in the
	 * velocities of their respective position and velocity components.
	 */
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(myPosition, myVelocity);
		for (Entity e : level.getEntitiesContaining(args)) {
			Component<Double> pos = e.getComponent(myPosition);
			double vel = e.getComponent(myVelocity).getValue();
			
			pos.setValue(pos.getValue() + vel * elapsedTime);
		}
	}
}
