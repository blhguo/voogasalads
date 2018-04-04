package voogasalad_callussalad;

public class Position implements Component {
	
	private double myXPos;
	private double myYPos;
	
	public Position(double xPos, double yPos) {
		myXPos = xPos;
		myYPos = yPos;
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
}
