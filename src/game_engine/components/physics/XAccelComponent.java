package game_engine.components.physics;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies what the acceleration in the x direction is
 * @author Kevin Deng, Ben Hubsch, Andy Nguyen, Jeremy Chen
 *
 */
@DataConditionable
public class XAccelComponent extends Component<Double> {

	public XAccelComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
