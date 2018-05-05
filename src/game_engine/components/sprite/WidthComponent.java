package game_engine.components.sprite;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the width of a sprite
 * @author Andy Nguyen, Ben Hubsch, Kevin Deng, Jeremy Chen
 *
 */
@DataConditionable
public class WidthComponent extends Component<Double> {
	
	public WidthComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
