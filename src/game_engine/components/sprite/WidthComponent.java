package game_engine.components.sprite;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class WidthComponent extends Component<Double> {
	
	public WidthComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
