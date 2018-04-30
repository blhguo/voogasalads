package game_engine.components.physics;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class XVelComponent extends Component<Double> {
	
	public XVelComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
