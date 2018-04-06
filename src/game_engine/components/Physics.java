package game_engine.components;

import java.util.Map;

import game_engine.Component;

public class Physics implements Component{
	
	private static final String ARG_ONE = "xVel";
	private static final String ARG_TWO = "yVel";
	private static final String ARG_THREE = "Acceleration";
	
	private double myXVel;
	private double myYVel;
	private double myAcceleration;
	
	/**
	 * creates a new instance of the Physics component
	 * @param xVel
	 * @param yVel
	 * @param a
	 */
	public Physics(Map<String, String> args) {
		myXVel = Double.parseDouble(args.get(ARG_ONE));
		myYVel = Double.parseDouble(args.get(ARG_TWO));
		myAcceleration = Double.parseDouble(args.get(ARG_THREE));
	}
	
	/**
	 * returns the x velocity of this Physics component
	 * @return myXVelocity
	 */
	public double getXVel() {
		return myXVel;
	}
	
	/**
	 * returns the y velocity of this Physics component
	 * @return myYVelocity
	 */
	public double getYVel() {
		return myYVel;
	}
	
	/**
	 * returns the acceleration of this physics component
	 * @return myAcceleration
	 */
	public double getAccel() {
		return myAcceleration;
	}
	
	public void setXVel(double xVel) {
		myXVel = xVel;
	}
	
	public void setYVel(double yVel) {
		myYVel = yVel;
	}
	
}
