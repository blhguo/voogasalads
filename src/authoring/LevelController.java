package authoring;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;

import game_engine.Level;

public class LevelController {

	ArrayList<Level> currentLevels;	
=======

import game_engine.Level;


public class LevelController {

	ArrayList<Level> currentLevels;			
>>>>>>> chestAbs
	
	public LevelController() {
		currentLevels = new ArrayList<Level>();
	}
	
	public void addLevel(Level l) {
		currentLevels.add(l);
	}
	
	public List<Level> getLevels() {
		return currentLevels;
	}
}
