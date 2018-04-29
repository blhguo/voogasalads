package game_engine.systems.velocity;

import game_engine.Component;
import game_engine.components.physics.YAccelComponent;
import game_engine.components.physics.YVelComponent;

public class VerticalVelocitySystem extends VelocitySystem {

	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;
	private static final Class<? extends Component<Double>> Y_ACCEL = YAccelComponent.class;
	
	public VerticalVelocitySystem() {
		super(Y_VEL, Y_ACCEL);
	}

}
