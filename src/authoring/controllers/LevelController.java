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
 * Contains Engine object - works with LevelPane, StoryBoardPane, and EntityController to update Engine object
 */
public class LevelController {

	private Engine engine;
	  
	/**
	 * Public constructor. Creates an engine object and adds an initial level to the engine.
	 */
	public LevelController() {
		engine = new Engine();
		addLevel();
	}
	/**
	 * Adds a new level to the engine. Sets default components for level to avoid null pointer
	 */
	public void addLevel() {
		Level newLevel = engine.createLevel();
		// add defaults to level
		int levelNum = newLevel.getId() + 1;
		newLevel.addComponent(new LevelNameComponent("Level " + String.valueOf(levelNum)));
		newLevel.addComponent(new LevelBackgroundComponent(AuthRes.getString("BackgroundDefault")));
		newLevel.addComponent(new LevelThumbComponent(AuthRes.getString("ThumbDefault")));
		newLevel.addComponent(new LevelHScrollComponent(true));
		newLevel.addComponent(new LevelVScrollComponent(true));
		engine.setLevel(newLevel.getId());
	}
	
	/**
	 * Returns engine object
	 * @return Engine
	 */
	public Engine getEngine(){
		return engine;
	}
	
	/**
	 * Sets engine object - used when loading game from a file
	 * @param e
	 */
	public void setEngine(Engine e){
		engine = e;
	}
	
	/**
	 * Special case of engine.getLevelPreviews() with only one component. Method simplifies LevelPane code
	 * @param comp
	 * @return <T> List<Object>
	 */
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
	
	/**
	 * Calls engine addComponent method. Simplifies LevelPane/StoryBoardPane code
	 * @param c
	 */
	public void addComp(Component<?> c){
		engine.getLevel().addComponent(c);
	}
	
	/**
	 * Adds entity to current Level
	 * @param e
	 */
	public void addEntity(Entity e){
		engine.getLevel().addEntity(e);
	}

	/**
	 * Adds event to current Level
	 * @param event
	 */
	public void addEvent(Event event){
		engine.getLevel().addEvent(event);
	}
	
}
