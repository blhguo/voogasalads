package game_engine.components;

import game_engine.Component;

public class Physics implements Component{
	private double myXVel;
	private double myYVel;
	private double myAcceleration;
	
	/**
	 * creates a new instance of the Physics component
	 * @param xVel
	 * @param yVel
	 * @param a
	 */
	public Physics(double xVel, double yVel, double a){
		myXVel = xVel;
		myYVel = yVel;
		myAcceleration = a;
	}
	
	/**
	 * returns the x velocity of this Physics component
	 * @return myXVelocity
	 */
	public double getXVel(){
		return myXVel;
	}
	
	/**
	 * returns the y velocity of this Physics component
	 * @return myYVelocity
	 */
	public double getYVel(){
		return myYVel;
	}
	
	/**
	 * returns the acceleration of this physics component
	 * @return myAcceleration
	 */
	public double getAccel(){
		return myAcceleration;
	}
	
	public void setXVel(double xVel) {
		myXVel = xVel;
	}
	
	public void setYVel(double yVel) {
		myYVel = yVel;
	}
	
}
