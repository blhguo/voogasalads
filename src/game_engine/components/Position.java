package game_engine.components;

import java.util.List;

import game_engine.Component;

public class Position implements Component {
	
	private double myXPos;
	private double myYPos;
	
	public Position(List<String> args) {
		myXPos = Double.parseDouble(args.get(0));
		myYPos = Double.parseDouble(args.get(1));
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
}
