package game_engine.components.sprite;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the order that the entity appears on screen
 * @author Andy Nguyen, Ben Hubsch, Kevin Deng, Jeremy Chen
 *
 */
@DataConditionable
public class ZHeightComponent extends Component<Double> {

	public ZHeightComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
