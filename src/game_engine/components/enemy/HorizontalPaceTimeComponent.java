package game_engine.components.enemy;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class HorizontalPaceTimeComponent extends Component<Double> {

	public HorizontalPaceTimeComponent(String val) {
		super(Double.parseDouble(val));
	}

}
