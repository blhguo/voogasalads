package authoring;

import java.util.ArrayList;
import java.util.List;

import gameData.ManipData;
import game_engine.Level;


public class LevelController {

	private ArrayList<Level> currentLevels;	
	private ManipData data;
	
	public LevelController() {
		currentLevels = new ArrayList<Level>();
		data = new ManipData();
	}
	
	public void addLevel(Level l) {
		currentLevels.add(l);
	}
	
	public List<Level> getLevels() {
		return currentLevels;
	}
	
	public void saveGame(){
		data.saveData(currentLevels);
	}
}
