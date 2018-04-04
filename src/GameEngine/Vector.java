package GameEngine;

public abstract class Vector {
	
	private double myA;
	private double myB;
	
	public Vector(double a, double b) {
		myA = a;
		myB = b;
	}
	
	protected double getA() {
		return myA;
	}
	
	protected double getB() {
		return myB;
	}
}
