package game_engine.components;

import java.util.List;

import game_engine.Component;

public class HealthComponent implements Component {
	
	private int myHealth;
	
	public HealthComponent(List<String> args){
		myHealth = Integer.parseInt(args.get(0));
	}
	
	public int getHealth(){
		return myHealth;
	}
	
	public void setHealth(int health){
		myHealth = health;
	}
	

	@Override
	public String getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
