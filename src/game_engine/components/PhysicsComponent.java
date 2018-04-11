package game_engine.components;

import java.util.List;

import game_engine.Component;

public class PhysicsComponent implements Component{
	private double myCurrXVel;
	private double myCurrYVel;
	private double myAcceleration;

	/**
	 * creates a new instance of the Physics component
	 * @param xVel
	 * @param yVel
	 * @param a
	 */
	public PhysicsComponent(List<String> args) {
		myCurrXVel = Double.parseDouble(args.get(0));
		myCurrYVel = Double.parseDouble(args.get(1));
		myAcceleration = Double.parseDouble(args.get(2));
	}
	
	public double getCurrXVel() {
		return myCurrXVel;
	}
	
	public double getCurrYVel(){
		return myCurrYVel;
	}

	/**
	 * returns the acceleration of this physics component
	 * @return myAcceleration
	 */
	public double getAccel() {
		return myAcceleration;
	}
	
	public void setCurrYVel(double yVel) {
		myCurrYVel = yVel;
	}
	
	public void setCurrXVel(double xVel) {
		myCurrXVel = xVel;
	}

	@Override
	public String getValues() {
		return null;
	}

	@Override
	public String getName() {
		return "Physics";
	}
}
