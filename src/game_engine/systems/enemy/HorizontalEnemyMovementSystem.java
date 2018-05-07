package game_engine.systems.enemy;

import game_engine.Component;
import game_engine.components.enemy.DefaultHorizontalPaceTimeComponent;
import game_engine.components.enemy.HorizontalPaceTimeComponent;
import game_engine.components.physics.XVelComponent;

/**
 * 
 * @author Ben Hubsch
 * The purpose of this class is to allow for the feature of having enemy entities that pace back and forth horizontally
 *
 */
public class HorizontalEnemyMovementSystem extends EnemyMovementSystem {
	
	private static final Class<? extends Component<Double>> DEFAULT_HORIZ_TIME = DefaultHorizontalPaceTimeComponent.class;
	private static final Class<? extends Component<Double>> CURR_HORIZ_TIME = HorizontalPaceTimeComponent.class;
	private static final Class<? extends Component<Double>> CURR_HORIZ_VEL = XVelComponent.class;
	
	/**
	 * instantiates a new HorizontalEnemyMovementSystem
	 */
	public HorizontalEnemyMovementSystem() {
		super(DEFAULT_HORIZ_TIME, CURR_HORIZ_TIME, CURR_HORIZ_VEL);
	}

}
