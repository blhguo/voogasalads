package game_engine.components.physics;

import game_engine.Component;

public class DefaultXVelocityComponent implements Component{
	
	private double myDefaultXVel;

	public DefaultXVelocityComponent(String value) {
		myDefaultXVel = Double.parseDouble(value);
	}

	@Override
	public String getValue() {
		return Double.toString(myDefaultXVel);
	}

	@Override
	public void setValue(String value) {
		myDefaultXVel = Double.parseDouble(value);
	}

}
