package game_engine.components.physics;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class YVelComponent extends Component<Double> {

	public YVelComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
