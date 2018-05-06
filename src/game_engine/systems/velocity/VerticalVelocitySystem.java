package game_engine.systems.velocity;

import game_engine.Component;
import game_engine.components.physics.YAccelComponent;
import game_engine.components.physics.YVelComponent;

/**
 * 
 * @author Kevin Deng
 * The purpose of this class is to handle velocity changes (acceleration) in the vertical direction
 *
 */
public class VerticalVelocitySystem extends VelocitySystem {

	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;
	private static final Class<? extends Component<Double>> Y_ACCEL = YAccelComponent.class;
	
	/**
	 * instantiates a new VerticalVelocitySystem
	 */
	public VerticalVelocitySystem() {
		super(Y_VEL, Y_ACCEL);
	}

}
