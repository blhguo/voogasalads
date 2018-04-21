package game_engine.components;

import game_engine.Component;

public class HealthComponent extends Component<Double> {
	public HealthComponent(String arg){
		super(Double.parseDouble(arg));
	}
}
