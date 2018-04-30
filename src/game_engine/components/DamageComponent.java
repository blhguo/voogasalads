package game_engine.components;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class DamageComponent extends Component<Double>{
	
	public DamageComponent(String arg){
		super(Double.parseDouble(arg));
	}

}
