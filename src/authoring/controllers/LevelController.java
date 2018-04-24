package authoring.controllers;

import java.util.ArrayList;

import gameData.ManipData;
import game_engine.Entity;
import game_engine.Level;

/**
 * @author Jennifer Chin
 * Maintains control of all active entities and the levels in which they reside, passes data to Data
 */
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
	/**
	 * @param l Adds the specifed level to current levels
	 */
	public void addLevel(Level l) {
		currentLevels.add(l);
		activeLevel = l;
	}

	/**
	 * Passes the current levels array to data
	 */
	public void saveGame() {
		currentLevels.stream().forEach(e -> System.out.println(e));
		for (Entity e : currentLevels.get(0).getEntities()){
			System.out.println(e);
		}
		System.out.println("currentLevels: " + currentLevels);
		data.saveData(currentLevels);
		//or .saveData(currentLevels, currentAttributes)
	}

	/**
	 * @return the active level
	 */
	public Level getActiveLevel(){
		return activeLevel;
	}
	
	public void setActiveLevel(Level l){
		activeLevel = l;
	}
	
	public ArrayList<Level> getLevels(){
		return currentLevels;
	}
	
}
