package game_engine.components;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class TimerComponent extends Component<Double>{

	public TimerComponent(String val) {
		super(Double.parseDouble(val));
	}

}
