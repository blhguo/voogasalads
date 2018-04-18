package game_engine.components;
import java.util.List;

import game_engine.Component;

/**
 * @author: Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * This component stores the X-Position, Y-Position, and rotation angle of an entity
 */
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

	public void setAngle(double angle){
		myAngle = angle;
	}

	@Override
	public String getValues() {
		String values = "xPos,d,"+ getX() + ";yPos,d," + getY() + ";Angle,d," + getAngle();
		return values;
	}

	@Override
	public String getName() {
		return "Position";
	}
	
	
}
