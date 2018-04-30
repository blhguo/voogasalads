package game_engine.systems.enemy;

import game_engine.Component;
import game_engine.components.enemy.DefaultVerticalPaceTimeComponent;
import game_engine.components.enemy.VerticalPaceTimeComponent;
import game_engine.components.physics.YVelComponent;

public class VerticalEnemyMovementSystem extends EnemyMovementSystem {

	private static final Class<? extends Component<Double>> DEFAULT_VERT_TIME = DefaultVerticalPaceTimeComponent.class;
	private static final Class<? extends Component<Double>> CURR_VERT_TIME = VerticalPaceTimeComponent.class;
	private static final Class<? extends Component<Double>> CURR_VERT_VEL = YVelComponent.class;
	
	public VerticalEnemyMovementSystem() {
		super(DEFAULT_VERT_TIME, CURR_VERT_TIME, CURR_VERT_VEL);
	}
	
}
