package game_engine.components;

import java.util.List;

import game_engine.Component;

public class CollidableComponent implements Component {
	
	private boolean myCollidable;
	
	public CollidableComponent(List<String> args) {
		myCollidable = Boolean.parseBoolean(args.get(0));
	}
	
	public boolean getPassable() {
		return myCollidable;
	}
	
	public void setPassible(boolean passable) {
		myCollidable = passable;
	}
}
