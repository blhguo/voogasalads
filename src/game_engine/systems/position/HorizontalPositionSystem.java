package game_engine.systems.position;

import game_engine.Component;
import game_engine.components.physics.XVelComponent;
import game_engine.components.position.XPosComponent;

public class HorizontalPositionSystem extends PositionSystem {
	
	private static final Class<? extends Component<Double>> X_POSITION = XPosComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	
	public HorizontalPositionSystem() {
		super(X_POSITION, X_VEL);
	}

}
