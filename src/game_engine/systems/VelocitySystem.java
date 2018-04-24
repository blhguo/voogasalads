package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.physics.XAccelComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YAccelComponent;
import game_engine.components.physics.YVelComponent;

public class VelocitySystem extends GameSystem {
	private static final Class<? extends Component<Double>> X_ACCEL = XAccelComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	private static final Class<? extends Component<Double>> Y_ACCEL = YAccelComponent.class;
	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;

	/**
	 * Creates a new instance of the MovementSystem class
	 * @param engine
	 */
	public VelocitySystem(Engine engine) {
		super(engine);
	}

	public void act(double elapsedTime) {
		List<Class<? extends Component<?>>> args = Arrays.asList(X_ACCEL, X_VEL, Y_ACCEL, Y_VEL);
		for (Entity e : getEngine().getEntitiesContaining(args)) {
			Component<Double> xVel = e.getComponent(X_VEL);
			Component<Double> yVel = e.getComponent(Y_VEL);
			double xAccel = e.getComponent(X_ACCEL).getValue();
			double yAccel = e.getComponent(X_ACCEL).getValue();

			xVel.setValue(xVel.getValue() + -1 * xAccel * elapsedTime);
			yVel.setValue(yVel.getValue() + -1 * yAccel * elapsedTime);
		}
	}


}
