package game_engine.components.enemy;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 *Specifies the oscillation period of an entity that can pace back and forth horizontally
 * @author Ben Hubsch
 *
 */
@DataConditionable
public class VerticalPaceTimeComponent extends Component<Double> {

	public VerticalPaceTimeComponent(String val) {
		super(Double.parseDouble(val));
	}

}
