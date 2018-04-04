package GameEngine.Components;

import GameEngine.Component;

public class Physics implements Component{
	private double myXVelocity;
	private double myYVelocity;
	private double myAcceleration;
	
	public Physics(double xVel, double yVel, double a){
		myXVelocity = xVel;
		myYVelocity = yVel;
		myAcceleration = a;
	}
	
	public double getXVel(){
		return myXVelocity;
	}
	
	public double getYVel(){
		return myYVelocity;
	}
	
	public double getAccel(){
		return myAcceleration;
	}
	
}
