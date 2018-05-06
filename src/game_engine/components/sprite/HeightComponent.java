package game_engine.components.sprite;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the height of a sprite
 * @author Andy Nguyen, Ben Hubsch, Kevin Deng, Jeremy Chen
 *
 */
@DataConditionable
public class HeightComponent extends Component<Double> {
	
	public HeightComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
