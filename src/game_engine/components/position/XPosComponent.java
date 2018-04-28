package game_engine.components.position;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class XPosComponent extends Component<Double> {
	
	public XPosComponent(String arg) {
		super(Double.parseDouble(arg));
	}
	
}
