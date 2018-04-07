package game_engine.components;

import java.util.List;

import game_engine.Component;

public class Physics implements Component{

	private double myMaxXVel;
	private double myMaxYVel;
	private double myCurrXVel;
	private double myCurrYVel;
	private double myAcceleration;
	
	/**
	 * creates a new instance of the Physics component
	 * @param xVel
	 * @param yVel
	 * @param a
	 */
	public Physics(List<String> args) {
		myMaxXVel = Double.parseDouble(args.get(0));
		myMaxYVel = Double.parseDouble(args.get(1));
		myCurrXVel = myMaxXVel;
		myCurrYVel = myMaxYVel;
		myAcceleration = Double.parseDouble(args.get(2));
	}
	
	/**
	 * returns the x velocity of this Physics component
	 * @return myXVelocity
	 */
	public double getMaxXVel() {
		return myMaxXVel;
	}
	
	/**
	 * returns the y velocity of this Physics component
	 * @return myYVelocity
	 */
	public double getMaxYVel() {
		return myMaxYVel;
	}
	
	public double getCurrXVel() {
		return myCurrXVel;
	}
	
	public double getCurrYVel() {
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
	
}
