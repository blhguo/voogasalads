package game_engine.components;

import game_engine.Component;

import java.util.List;

public class PhysicsComponent implements Component{
	private double myXVelocity;
	private double myYVelocity;
	private double myAcceleration;

	/**
	 *
	 * @param args
	 */
	public PhysicsComponent(List<String> args){
		myXVelocity = Double.parseDouble(args.get(0));
		myYVelocity = Double.parseDouble(args.get(1));
		myAcceleration = Double.parseDouble(args.get(2));
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
