package game_engine;

public abstract class Vector {
	
	private double myX;
	private double myY;
	
	public Vector(double x, double y) {
		myX = x;
		myY = y;
	}
	
	public double getX() {
		return myX;
	}
	
	public double getY() {
		return myY;
	}
}
