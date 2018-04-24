package authoring.controllers;

import java.util.ArrayList;

import gameData.ManipData;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.level.Level;

/**
 * @author Jennifer Chin
 * Maintains control of all active entities and the levels in which they reside, passes data to Data
 */
public class LevelController {

	private ManipData data;
	private Engine engine;
	
	public LevelController() {
		engine = new Engine();
		data = new ManipData();
	}
	/**
	 * @param l Adds the specifed level to current levels
	 */
	public void addLevel() {
		Level newLevel = engine.createLevel();
		engine.setLevel(newLevel.getId());
	}

	/**
	 * Passes the current levels array to data
	 */
	public void saveGame() {
		// need to get map of levels from engine to pass to data
		//data.saveData(currentLevels);
		//or .saveData(currentLevels, currentAttributes)
	}

	/**
	 * @return the active level
	 */
	public Level getActiveLevel(){
		return engine.getLevel();
	}
	
	public void setActiveLevel(Level l){
		engine.setLevel(l.getId());
	}
	
//	public ArrayList<Level> getLevels(){
//		return currentLevels;
//	}
	
}
