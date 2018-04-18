package game_engine.components.physics;

import game_engine.Component;

public class XVelocityComponent implements Component{
	
	private double myXVel;

	public XVelocityComponent(String value) {
		myXVel = Double.parseDouble(value);
	}

	@Override
	public String getValue() {
		return Double.toString(myXVel);
	}

	@Override
	public void setValue(String value) {
		myXVel = Double.parseDouble(value);
	}

}
