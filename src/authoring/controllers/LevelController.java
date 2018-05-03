package authoring.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.event.Event;
import game_engine.level.Level;
import game_engine.level.LevelBackgroundComponent;
import game_engine.level.LevelHScrollComponent;
import game_engine.level.LevelNameComponent;
import game_engine.level.LevelThumbComponent;
import game_engine.level.LevelVScrollComponent;
import resources.keys.AuthRes;

/**
 * @author Jennifer Chin
 * Maintains control of all active entities and the levels in which they reside, passes data to Data
 */
public class LevelController {

	private Engine engine;
	
	public LevelController() {
		engine = new Engine();
		addLevel();
	}
	/**
	 * Adds a new level to the engine
	 */
	public void addLevel() {
		// need to have splash screen also
		Level newLevel = engine.createLevel();
		// add defaults to level
		int levelNum = newLevel.getId() + 1;
		newLevel.addComponent(new LevelNameComponent("Level " + String.valueOf(levelNum)));
		newLevel.addComponent(new LevelBackgroundComponent(AuthRes.getString("BackgroundDefault")));
		newLevel.addComponent(new LevelThumbComponent(AuthRes.getString("ThumbDefault")));
		newLevel.addComponent(new LevelHScrollComponent(true));
		newLevel.addComponent(new LevelVScrollComponent(true));
		engine.setLevel(newLevel.getId());
		System.out.println("BEFORE SAVE: " + engine.getLevel());
	}
	
	public Engine getEngine(){
		return engine;
	}
	
	public void setEngine(Engine e){
		engine = e;
	}
	
	public <T> List<Object> getSingleCompList(Class<? extends Component<T>> comp){
		List<Object> ret = new ArrayList<Object>();
		Map<Integer, List<Component<T>>> map = engine.getLevelPreviews(Arrays.asList(comp));
		for (List<Component<T>> list: map.values()){
			for (Component<T> c: list){
				ret.add(c.getValue());
			}
		}
		return ret;
	}
	
	public void addComp(Component<?> c){
		engine.getLevel().addComponent(c);
	}
	
	public void addEntity(Entity e){
		System.out.println("Entity: " + e);
		engine.getLevel().addEntity(e);
		System.out.println("------ Entities in the level ------");
		engine.getLevel().getEntities().stream().forEach(a -> 
			System.out.println(a));
	}

	public void addEvent(Event event){
		engine.getLevel().addEvent(event);
		System.out.println(event.getActions());
		System.out.println(event.getConditions());
	}
	
}
