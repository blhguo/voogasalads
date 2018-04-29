package game_engine.systems.velocity;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.level.Level;

public abstract class VelocitySystem extends GameSystem {
	
	private Class<? extends Component<Double>> myVelocity;
	private Class<? extends Component<Double>> myAcceleration;
	
	public VelocitySystem(Class<? extends Component<Double>> velocity, Class<? extends Component<Double>> acceleration) {
		myVelocity = velocity;
		myAcceleration = acceleration;
	}

	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(myVelocity, myAcceleration);
		for (Entity e : level.getEntitiesContaining(args)) {
			Component<Double> vel = e.getComponent(myVelocity);
			double accel = e.getComponent(myAcceleration).getValue();
			vel.setValue(vel.getValue() + -1 * accel * elapsedTime);
		}
	}


}
