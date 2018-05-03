package game_engine.components.enemy;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class VerticalPaceTimeComponent extends Component<Double> {

	public VerticalPaceTimeComponent(String val) {
		super(Double.parseDouble(val));
	}

}
