package game_engine.components;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class HealthComponent extends Component<Double> {
	public HealthComponent(String arg){
		super(Double.parseDouble(arg));
	}
}
