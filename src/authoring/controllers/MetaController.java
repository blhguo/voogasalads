package authoring.controllers;

import java.util.HashMap;
import java.util.Map;

import gameData.ManipData;
import resources.keys.AuthRes;

public class MetaController {
	
	private ManipData data;
	private LevelController lcontroller;
	private String gameName;
	private Map<String, String> printMap;
	private Map<String, String> configMap;
	
	public MetaController(LevelController lc){
		data = new ManipData();
		lcontroller = lc;
		initMaps();
	}
	
	/**
	 * Passes the current levels array to data
	 */
	public void saveGame() {
		//data.saveData(lcontroller.getEngine(), gameName, printMap);
		data.saveData(lcontroller.getEngine(), gameName, gameName, printMap, configMap);
	}
	
	public void setGameName(String name){
		gameName = name;
		printMap.put(AuthRes.getString("Name"), name);
		configMap.put(AuthRes.getStringKeys("key0"), name);
	}
	
	public String getGameName(){
		return gameName;
	}
	
	public Map<String, String> getPrintMap(){
		return printMap;
	}
	
	public void setPrintMap(Map<String, String> newMap){
		printMap = newMap;
		System.out.println("MAP NAME: " + printMap.get(AuthRes.getString("Name")));
		setGameName(printMap.get(AuthRes.getString("Name")));
	}
	
	public Map<String, String> getConfigMap(){
		return configMap;
	}
	
	private void initMaps(){
		printMap = new HashMap<String, String>();
		configMap = new HashMap<String, String>();
		
		setGameName(AuthRes.getString("NameDefault"));
		configMap.put(AuthRes.getStringKeys("key2"), AuthRes.getString("AuthorDefault"));
		configMap.put(AuthRes.getStringKeys("key1"), AuthRes.getString("ThumbnailDefault"));
		printMap.put(AuthRes.getString("Author"), AuthRes.getString("AuthorDefault"));
		printMap.put(AuthRes.getString("Rules"), AuthRes.getString("RulesDefault"));
	}

}
