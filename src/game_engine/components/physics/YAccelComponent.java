package game_engine.components.physics;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the current Y acceleration of an entity
 * @author Kevin Deng, Ben Hubsch, Andy Nguyen, Jeremy Chen
 *
 */
@DataConditionable
public class YAccelComponent extends Component<Double> {
	
	public YAccelComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
