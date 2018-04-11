package game_engine.components.physics;

import java.util.List;

import game_engine.Component;

public abstract class PhysicsComponent implements Component {
	private double myCurrVel;
	private double myDefaultVel;
	private double myAccel;
	
	public PhysicsComponent(List<String> args) {
		myDefaultVel = Double.parseDouble(args.get(0));
		myCurrVel = 0;
		myAccel = Double.parseDouble(args.get(1));
	}
	
	public double getCurrVel() {
		return myCurrVel;
	}
	
	public double getDefaultVel() {
		return myDefaultVel;
	}
	
	public double getAccel() {
		return myAccel;
	}
	
	public void setCurrVel(double vel) {
		myCurrVel = vel;
	}
	
}
