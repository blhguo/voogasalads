package game_engine.systems.velocity;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.level.Level;

/**
 * 
 * @author Kevin Deng
 * The purpose of this system is to handle velocity changes in a particular direction. This allows
 * entities containing the respective velocity and acceleration components to be able to be accelerated.
 *
 */
public abstract class VelocitySystem implements GameSystem {
	
	private Class<? extends Component<Double>> myVelocity;
	private Class<? extends Component<Double>> myAcceleration;
	
	/**
	 * instantiates a new VelocitySystem with the given velocity and acceleration components
	 * @param velocity
	 * @param acceleration
	 */
	public VelocitySystem(Class<? extends Component<Double>> velocity, Class<? extends Component<Double>> acceleration) {
		myVelocity = velocity;
		myAcceleration = acceleration;
	}

	/**
	 * accelerates entities (in a given direction) containing the corresponding velocity and acceleration components
	 */
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(myVelocity, myAcceleration);
		for (Entity e : level.getEntitiesContaining(args)) {
			Component<Double> vel = e.getComponent(myVelocity);
			double accel = e.getComponent(myAcceleration).getValue();
			vel.setValue(vel.getValue() + -1 * accel * elapsedTime);
		}
	}


}
