package game_engine.components;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

/**
 * The TimerComponent defines a time threshold of the entity. It is used in Conditions and Actions
* @author Kevin Deng, Andy Nguyen, Ben Hubsch, Jeremy Chen *
 */
@DataConditionable
public class TimerComponent extends Component<Double>{

	public TimerComponent(String val) {
		super(Double.parseDouble(val));
	}

}
