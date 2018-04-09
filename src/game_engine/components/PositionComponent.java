package game_engine.components;
import game_engine.Component;

import java.util.List;

public class PositionComponent implements Component {
	
	private double myXPos;
	private double myYPos;
	private double myAngle;
	
	public PositionComponent(List<String> args){
			//double xPos, double yPos) {
		myXPos = Double.parseDouble(args.get(0));
		myYPos = Double.parseDouble(args.get(1));
		myAngle = Double.parseDouble(args.get(2));
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public void setX(double x){
		myXPos = x;
	}
	
	public void setY(double y){
		myYPos = y;
	}

	public double getAngle() {
		return myAngle;
	}
}