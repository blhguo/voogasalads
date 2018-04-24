package authoring.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import gameData.ManipData;
import game_engine.Component;
import game_engine.Engine;
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
//	public Level getActiveLevel(){
//		return engine.getLevel();
//	}
//	
//	public void setActiveLevel(Level l){
//		engine.setLevel(l.getId());
//	}
	
	public Engine getEngine(){
		return engine;
	}
	
	// may need to change from string to generic depending on what this method gets used for
	public ArrayList<Object> getSingleCompList(Class<? extends Component<?>> comp){
		ArrayList<Object> ret = new ArrayList<Object>();
		Map<Integer, List<Component>> map = engine.getLevelPreviews(Arrays.asList(comp));
		for (List<Component> list: map.values()){
			for (Component c: list){
				ret.add(c.getValue());
			}
		}
		return ret;
	}
	
}
