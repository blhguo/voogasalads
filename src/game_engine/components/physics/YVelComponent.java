package game_engine.components.physics;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the current y velocity of an entity
 * @author Kevin Deng, Ben Hubsch, Andy Nguyen, Jeremy Chen
 *
 */
@DataConditionable
public class YVelComponent extends Component<Double> {
	
	private static final double STARTING_VEL = 0.0;

	public YVelComponent(String arg) {
		super(STARTING_VEL);
	}

}
