package game_engine.components;

import game_engine.Component;

public class HealthComponent extends Component<Integer> {
	public HealthComponent(String arg){
		super(Integer.parseInt(arg));
	}
}
