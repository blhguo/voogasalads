package game_engine.components.physics;

import game_engine.Component;

public class YAccelerationComponent implements Component{
	
	private double myYAccel;

	public YAccelerationComponent(String value) {
		myYAccel = Double.parseDouble(value);
	}

	@Override
	public String getValue() {
		return Double.toString(myYAccel);
	}

	@Override
	public void setValue(String value) {
		myYAccel = Double.parseDouble(value);
	}

}
