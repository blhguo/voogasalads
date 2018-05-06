package game_engine.components.physics;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies what the current x velocity is of an entity
 * @author Kevin Deng, Ben Hubsch, Andy Nguyen, Jeremy Chen
 *
 */
@DataConditionable
public class XVelComponent extends Component<Double> {
	
	private static final double STARTING_VEL = 0.0;
	
	public XVelComponent(String arg) {
		super(STARTING_VEL);
	}

}
