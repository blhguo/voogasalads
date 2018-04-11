package game_engine.components;
import game_engine.Component;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;

/**
 * @author: Jeremy Chen
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

	public String getValues() {
		String ret;
		ret = "XPos,d," + this.getX() + ";" + "YPos,d," + this.getY() + ";" + "Angle,d," + this.getAngle();
		return ret;
	}
	@Override
	public String getName() {
		return "Position";
	}
}
