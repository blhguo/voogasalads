package game_engine.components;

import game_engine.Component;

public class HealthComponent implements Component {
	private String myHealth;
	
	public HealthComponent(String arg){
		myHealth = arg;
	}

	@Override
	public String getValue() {
		return myHealth;
	}
	
	@Override
	public void setValue(String arg) {
		myHealth = arg;
	}
}
