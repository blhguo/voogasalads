package authoring.controllers;

import java.util.Map;
import java.util.Map.Entry;

import authoring.Canvas;
import authoring.right_components.LevelPane;
import authoring.right_components.StoryBoardPane;
import gameData.ManipData;
import game_engine.Engine;

public class Loader {

	private LevelController lcontroller;
	private Canvas canvas;
	private ManipData data;
	private LevelPane levelp;
	private MetaController mcontroller;
	private StoryBoardPane storyp;
	
	public Loader(Canvas c, LevelPane lp, StoryBoardPane story){
		canvas = c;
		data = new ManipData();
		levelp = lp;
		storyp = story;
	}
	
	public void loadGame(String gamePath, String metaPath){
		Engine engine = data.loadData(gamePath);
		lcontroller.setEngine(engine);
		lcontroller.getEngine().setLevel(0);
		levelp.update();
		
		Map<String, String> metaMap = data.openMeta(metaPath);
		mcontroller.setPrintMap(metaMap);
		storyp.update();
	}
	
	public void setLevelController(LevelController lc){
		lcontroller = lc;
	}
	
	public void setMetaController(MetaController mc){
		mcontroller = mc;
	}
	
}
