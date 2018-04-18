package game_engine.components.physics;

import game_engine.Component;

public class XAccelerationComponent implements Component{
	
	private double myXAccel;

	public XAccelerationComponent(String value) {
		myXAccel = Double.parseDouble(value);
	}

	@Override
	public String getValue() {
		return Double.toString(myXAccel);
	}

	@Override
	public void setValue(String value) {
		myXAccel = Double.parseDouble(value);
	}

}
