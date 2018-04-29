package authoring.controllers;

import authoring.Canvas;
import game_engine.level.Level;

public class Loader {

	private LevelController lcontroller;
	private Canvas canvas;
	
	public Loader(LevelController lc, Canvas c){
		lcontroller = lc;
		canvas = c;
	}
	
	public void loadGame(){
		
	}
	
	// called whenever new level is selected 
	public void loadLevel(){
		Level currLevel = lcontroller.getEngine().getLevel();
		
	}
	
}
