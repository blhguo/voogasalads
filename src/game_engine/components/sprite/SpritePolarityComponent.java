package game_engine.components.sprite;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the orientation (facing left or right) of a sprite given the direction it is moving towards
 * @author Jeremy Chen
 *
 */
@DataConditionable
public class SpritePolarityComponent extends Component<Double>{

	public SpritePolarityComponent(String val) {
		super(Double.parseDouble(val));
	}

}
