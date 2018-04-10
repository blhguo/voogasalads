package authoring.controllers;

import java.util.ArrayList;

import gameData.ManipData;
import game_engine.Level;


public class LevelController {

	private ArrayList<Level> currentLevels;	
	private ManipData data;
	private Level activeLevel;
	
	public LevelController() {
		currentLevels = new ArrayList<Level>();
		Level initLevel = new Level();
		addLevel(initLevel);
		activeLevel = initLevel;
		data = new ManipData();
	}
	
	public void addLevel(Level l) {
		currentLevels.add(l);
		activeLevel = l;
	}
	
	public void saveGame() {
		data.saveData(currentLevels); //or .saveData(currentLevels, currentAttributes)
	}
	
	public Level getActiveLevel(){
		return activeLevel;
	}
	
}
