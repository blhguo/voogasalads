package authoring.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import gameData.ManipData;
import game_engine.Component;
import game_engine.Engine;
import game_engine.level.Level;
import game_engine.level.LevelBackgroundComponent;
import game_engine.level.LevelNameComponent;

/**
 * @author Jennifer Chin
 * Maintains control of all active entities and the levels in which they reside, passes data to Data
 */
public class LevelController {

	private ManipData data;
	private Engine engine;
	private PaneController pcontroller;
	
	public LevelController(PaneController pc) {
		engine = new Engine();
		data = new ManipData();
		pcontroller = pc;
		addLevel();
	}
	/**
	 * @param l Adds the specifed level to current levels
	 */
	public void addLevel() {
		Level newLevel = engine.createLevel();
		// add defaults to level
		newLevel.addComponent(new LevelNameComponent("Level " + String.valueOf(newLevel.getId())));
		// not actually an image - default is just a string holder 
		newLevel.addComponent(new LevelBackgroundComponent("default"));
		//pcontroller.resetBackground();
		engine.setLevel(newLevel.getId());
	}

	/**
	 * Passes the current levels array to data
	 */
	public void saveGame() {
		// need all 3 parameters
		//data.saveData(currentLevels);
		//or .saveData(currentLevels, currentAttributes)
	}
	
	public Engine getEngine(){
		return engine;
	}
	
	public ArrayList<Object> getSingleCompList(Class<? extends Component<?>> comp){
		ArrayList<Object> ret = new ArrayList<Object>();
		Map<Integer, List<Component<?>>> map = engine.getLevelPreviews(Arrays.asList(comp));
		for (List<Component<?>> list: map.values()){
			for (Component<?> c: list){
				ret.add(c.getValue());
			}
		}
		return ret;
	}
	
	public void addComp(Component<?> c){
		engine.getLevel().addComponent(c);
	}
	
}
