package game_engine.systems.position;

import game_engine.Component;
import game_engine.components.physics.XVelComponent;
import game_engine.components.position.XPosComponent;

/**
 * 
 * @author Ben Hubsch, Andy Nguyen, Kevin Deng, Jeremy Chen
 * The purpose of this class is to handle position changes (movement) in the horizontal direction
 *
 */
public class HorizontalPositionSystem extends PositionSystem {
	
	private static final Class<? extends Component<Double>> X_POSITION = XPosComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	
	/**
	 * instantiates a new HorizontalPositionSystem
	 */
	public HorizontalPositionSystem() {
		super(X_POSITION, X_VEL);
	}

}
