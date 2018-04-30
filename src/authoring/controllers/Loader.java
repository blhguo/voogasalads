package authoring.controllers;

import authoring.Canvas;
import gameData.ManipData;
import game_engine.Engine;
import game_engine.level.Level;

public class Loader {

	private LevelController lcontroller;
	private Canvas canvas;
	private ManipData data;
	
	public Loader(LevelController lc, Canvas c){
		lcontroller = lc;
		canvas = c;
		data = new ManipData();
	}
	
	public void loadGame(String fpath){
		Engine engine = data.loadData(fpath);
		lcontroller.setEngine(engine);
	}
	
	// called whenever new level is selected 
	public void loadLevel(){
		Level currLevel = lcontroller.getEngine().getLevel();
		
	}
	
}
