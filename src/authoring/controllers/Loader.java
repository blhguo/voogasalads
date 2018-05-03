package authoring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.Canvas;
import authoring.right_components.LevelPane;
import authoring.right_components.StoryBoardPane;
import authoring.right_components.EntityComponent.EntityPane;
import authoring.right_components.EntityComponent.EntityWrapper;
import gameData.ManipData;
import game_engine.Engine;
import game_engine.Entity;

public class Loader {

	private LevelController lcontroller;
	private Canvas canvas;
	private ManipData data;
	private LevelPane levelp;
	private MetaController mcontroller;
	private StoryBoardPane storyp;
	private EntityPane entityp;
	
	public Loader(Canvas c, LevelPane lp, StoryBoardPane story, EntityPane entity){
		canvas = c;
		data = new ManipData();
		levelp = lp;
		storyp = story;
		entityp = entity;
	}
	
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
			//System.out.println("ENTITY: " + e.toString());
		}
		
		entityp.load(ewList);
	}
	
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}
	
	public void setMetaController(MetaController mc){
		mcontroller = mc;
	}
	
}
