package authoring.controllers;

import authoring.Canvas;
import authoring.right_components.LevelPane;
import gameData.ManipData;
import game_engine.Engine;
import game_engine.level.Level;

public class Loader {

	private LevelController lcontroller;
	private Canvas canvas;
	private ManipData data;
	private LevelPane levelp;
	
	public Loader(LevelController lc, Canvas c, LevelPane lp){
		lcontroller = lc;
		canvas = c;
		data = new ManipData();
		levelp = lp;
	}
	
	public void loadGame(String fpath){
		Engine engine = data.loadData(fpath);
		lcontroller.setEngine(engine);
		levelp.update();
	}
	
}
