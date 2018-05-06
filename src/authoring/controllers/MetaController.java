package authoring.controllers;

/**
 * @author Jennifer Chin
 * MetaController communicates with Firebase to save a game. It creates the maps that saveData takes as parameters.
 */

import java.util.HashMap;
import java.util.Map;

import gameData.Firebase;
import gameData.ManipData;
import resources.keys.AuthRes;

public class MetaController {
	
	private Firebase data;
	private LevelController lcontroller;
	private String gameName;
	private Map<String, String> printMap;
	private Map<String, String> configMap;
	
	/**
	 * Public MetaController Constructor. Takes in a LevelController to get the Engine to pass to Firebase. Instantiates a new Firebase object to call
	 * updateFirebase(). Initializes map parameters.
	 * @param lc
	 */
	public MetaController(LevelController lc){
		data = new Firebase();
		data.initListeners();
		lcontroller = lc;
		initMaps();
	}
	
	/**
	 * Resets current level in engine to the first level. Calls updateFirebase() to save game
	 */
	public void saveGame() {
		lcontroller.getEngine().setLevel(0);
		data.updateFirebase(lcontroller.getEngine(), gameName, gameName, printMap, configMap);
	}
	
	/**
	 * Sets the game name. Updates both maps with new name.
	 * @param name
	 */
	public void setGameName(String name){
		gameName = name;
		printMap.put(AuthRes.getString("Name"), name);
		configMap.put(AuthRes.getStringKeys("key0"), name);
	}
	
	/**
	 * Returns the current game name.
	 * @return String
	 */
	public String getGameName(){
		return gameName;
	}
	
	/**
	 * Returns the meta data map of Strings to be printed in Player
	 * @return Map<String, String>
	 */
	public Map<String, String> getPrintMap(){
		return printMap;
	}
	
//	public void setPrintMap(Map<String, String> newMap){
//		printMap = newMap;
//		setGameName(printMap.get(AuthRes.getString("Name")));
//	}
	
	/**
	 * Returns the meta data map that is the basis for the config.properties file of a game
	 * @return Map<String, String>
	 */
	public Map<String, String> getConfigMap(){
		return configMap;
	}
	
	private void initMaps(){
		printMap = new HashMap<>();
		configMap = new HashMap<>();
		
		setGameName(AuthRes.getString("NameDefault"));
		configMap.put(AuthRes.getStringKeys("key2"), AuthRes.getString("AuthorDefault"));
		configMap.put(AuthRes.getStringKeys("key1"), AuthRes.getString("ThumbnailDefault"));
		printMap.put(AuthRes.getString("Author"), AuthRes.getString("AuthorDefault"));
		printMap.put(AuthRes.getString("Rules"), AuthRes.getString("RulesDefault"));
	}

}
