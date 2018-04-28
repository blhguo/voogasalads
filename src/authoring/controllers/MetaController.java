package authoring.controllers;

import java.util.HashMap;
import java.util.Map;

import gameData.ManipData;
import resources.keys.AuthRes;

public class MetaController {
	
	private ManipData data;
	private LevelController lcontroller;
	private String gameName;
	private Map<String, String> metaMap;
	
	public MetaController(LevelController lc){
		data = new ManipData();
		lcontroller = lc;
		initMap();
	}
	
	/**
	 * Passes the current levels array to data
	 */
	public void saveGame() {
		data.saveData(lcontroller.getEngine(), gameName, metaMap);
	}
	
	public void setGameName(String name){
		gameName = name;
		metaMap.put("Name", name);
	}
	
	public String getGameName(){
		return gameName;
	}
	
	public Map<String, String> getMap(){
		return metaMap;
	}
	
	private void initMap(){
		metaMap = new HashMap<String, String>();
		setGameName("Game Name");
		metaMap.put(AuthRes.getString("author"), AuthRes.getString("author"));
		metaMap.put(AuthRes.getString("rules"), AuthRes.getString("rules"));
	}

}
