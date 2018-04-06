package game_engine.components;

import java.util.Map;

import game_engine.Component;

public class Position implements Component {
	
	private static final String ARG_ONE = "xPos";
	private static final String ARG_TWO = "yPos";
	
	private double myXPos;
	private double myYPos;
	
	public Position(Map<String, String> args) {
		myXPos = Double.parseDouble(args.get(ARG_ONE));
		myYPos = Double.parseDouble(args.get(ARG_TWO));
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
