package authoring;

import java.util.ArrayList;
import java.util.List;

import game_engine.Level;

public class LevelController {

	ArrayList<Level> currentLevels;	
	
	public LevelController() {
		currentLevels = new ArrayList<Level>();
	}
	
	public void addLevel(Level l) {
		currentLevels.add(l);
	}
	
	public List<Level> getLevels() {
		return currentLevels;
	}
	
	public void saveGame() {
		
	}
}
