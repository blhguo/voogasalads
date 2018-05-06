package authoring.controllers;

/**
 * @author Jennifer Chin
 * Loader controller used to communicate with ManipData to load a game. Contains several controller and pane objects in order to pass the new loaded information
 * to the appropriate classes.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.right_components.LevelPane;
import authoring.right_components.StoryBoardPane;
import authoring.right_components.EntityComponent.EntityPane;
import authoring.right_components.EntityComponent.EntityWrapper;
import gameData.ManipData;
import game_engine.Engine;
import game_engine.Entity;

public class Loader {

	private LevelController lcontroller;
	private ManipData data;
	private LevelPane levelp;
	private MetaController mcontroller;
	private StoryBoardPane storyp;
	private EntityPane entityp;
	
	/**
	 * Public Loader constructor. Instantiates new ManipData object that is used to load file. Takes in panes that need to be updated with new information
	 * @param lp
	 * @param story
	 * @param entity
	 */
	public Loader(LevelPane lp, StoryBoardPane story, EntityPane entity){
		data = new ManipData();
		levelp = lp;
		storyp = story;
		entityp = entity;
	}
	
	/**
	 * Calls ManipData loadData and openMeta methods to get relevant Engine and metaData map. Updates authoring environment accordingly.
	 * @param gamePath
	 * @param metaPath
	 */
	public void loadGame(String gamePath, String metaPath){
		Engine engine = data.loadData(gamePath);
		lcontroller.setEngine(engine);
		lcontroller.getEngine().setLevel(0);
		levelp.update();
		
		Map<String, String> metaMap = data.openMeta(metaPath);
		mcontroller.setPrintMap(metaMap);
		storyp.update();
		
		// will this load the entire game or only the first level
		List<EntityWrapper> ewList = new ArrayList<EntityWrapper>();
		List<Entity> entList = engine.getLevel().getEntities();
		for (Entity e: entList){
			EntityWrapper ew = new EntityWrapper(e, entityp);
			ew.setLevel(engine.getLevel().getId());
			ewList.add(ew);
		}
		
		entityp.load(ewList);
	}
	
	/**
	 * Sets LevelController for class
	 * @param lc
	 */
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}
	
	/**
	 * Sets MetaController for class
	 * @param mc
	 */
	public void setMetaController(MetaController mc){
		mcontroller = mc;
	}
	
	/**
	 * Returns EntityPane of class
	 * @return EntityPane
	 */
	public EntityPane getEntityPane(){
		return entityp;
	}
	
	/**
	 * Returns LevelPane of class
	 * @return LevelPane
	 */
	public LevelPane getLevelPane(){
		return levelp;
	}
	
	/**
	 * Returns StoryBoardPane of class
	 * @return
	 */
	public StoryBoardPane getStoryPane(){
		return storyp;
	}
	
}
