package game_engine.components;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class NumberOfJumpsAllowedComponent extends Component<Double> {
	
	private static final double STARTING_JUMPS = 0.0;
	
	public NumberOfJumpsAllowedComponent(String arg){
		super(STARTING_JUMPS);
	}
}
