package game_engine.systems.position;

import game_engine.Component;
import game_engine.components.physics.YVelComponent;
import game_engine.components.position.YPosComponent;

public class VerticalPositionSystem extends PositionSystem {
	
	private static final Class<? extends Component<Double>> Y_POSITION = YPosComponent.class;
	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;
	
	public VerticalPositionSystem() {
		super(Y_POSITION, Y_VEL);
	}

}
