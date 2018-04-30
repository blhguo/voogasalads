package game_engine.components.physics;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class XAccelComponent extends Component<Double> {

	public XAccelComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
