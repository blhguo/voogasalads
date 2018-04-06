package game_engine.components;

import java.util.Map;

import game_engine.Component;

public class Collidable implements Component {
	
	private static final String ARG_ONE = "Collidable";
	
	private boolean myCollidable;
	
	public Collidable(Map<String, String> args) {
		myCollidable = Boolean.parseBoolean(args.get(ARG_ONE));
	}
	
	public boolean getPassable() {
		return myCollidable;
	}
	
	public void setPassible(boolean passable) {
		myCollidable = passable;
	}
}
