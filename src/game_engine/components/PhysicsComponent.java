package game_engine.components;

import game_engine.Component;

public class PhysicsComponent implements Component{
	private double myXVelocity;
	private double myYVelocity;
	private double myAcceleration;
	
	/**
	 * creates a new instance of the PhysicsComponent component
	 * @param xVel
	 * @param yVel
	 * @param a
	 */
	public PhysicsComponent(double xVel, double yVel, double a){
		myXVelocity = xVel;
		myYVelocity = yVel;
		myAcceleration = a;
	}
	
	/**
	 * returns the x velocity of this PhysicsComponent component
	 * @return myXVelocity
	 */
	public double getXVel(){
		return myXVelocity;
	}
	
	/**
	 * returns the y velocity of this PhysicsComponent component
	 * @return myYVelocity
	 */
	public double getYVel(){
		return myYVelocity;
	}
	
	/**
	 * returns the acceleration of this physics component
	 * @return myAcceleration
	 */
	public double getAccel(){
		return myAcceleration;
	}
	
}
