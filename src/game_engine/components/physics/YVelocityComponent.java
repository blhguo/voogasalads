package game_engine.components.physics;

import game_engine.Component;

public class YVelocityComponent implements Component{
	
	private double myYVel;

	public YVelocityComponent(String value) {
		myYVel = Double.parseDouble(value);
	}

	@Override
	public String getValue() {
		return Double.toString(myYVel);
	}

	@Override
	public void setValue(String value) {
		myYVel = Double.parseDouble(value);
	}

}
