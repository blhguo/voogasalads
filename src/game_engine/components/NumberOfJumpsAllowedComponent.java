package game_engine.components;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class NumberOfJumpsAllowedComponent extends Component<Double> {
	public NumberOfJumpsAllowedComponent(String arg){
		super(Double.parseDouble(arg));
	}
}
