package game_engine.components.physics;

import game_engine.Component;

public class DefaultYVelocityComponent implements Component{
	
	private double myDefaultYVel;

	public DefaultYVelocityComponent(String value) {
		myDefaultYVel = Double.parseDouble(value);
	}

	@Override
	public String getValue() {
		return Double.toString(myDefaultYVel);
	}

	@Override
	public void setValue(String value) {
		myDefaultYVel = Double.parseDouble(value);
	}


}
