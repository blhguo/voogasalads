package authoring.controllers;

import java.util.HashMap;
import java.util.Map;

import gameData.ManipData;

public class MetaController {
	
	private ManipData data;
	private LevelController lcontroller;
	private String gameName;
	private Map<String, String> metaMap;
	
	public MetaController(LevelController lc){
		data = new ManipData();
		lcontroller = lc;
		gameName = "Game Name";
		metaMap = new HashMap<String, String>();
	}
	
	/**
	 * Passes the current levels array to data
	 */
	public void saveGame() {
		// need all 3 parameters
		data.saveData(lcontroller.getEngine(), gameName, metaMap);
	}
	
	public void setGameName(String name){
		gameName = name;
	}
	
	public String getGameName(){
		return gameName;
	}

}
