package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.keyboard.DownKeyboardComponent;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.keyboard.RightKeyboardComponent;
import game_engine.components.keyboard.UpKeyboardComponent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class DataManager {
	private Map<KeyCode,String> keyPrefs;
	private Map<String,KeyCode> keyPrefsReversed;
	private Map<String,KeyCode> engineMap;
	private List<String> gameInputs;
	private Engine gameEngine;
	private Entity gamePlayer;
	private String gameTitle = "VOOGA"; //TODO get from config data
	private Stage gameStage;
	
	/**
	 * Constructor to create an instance prior to initialization.
	 */
	public DataManager() {
		//TODO something
	}
	
	/**
	 * Method called to initialize the class after creation.
	 * 
	 * @param storage
	 */
	public void initialize(InstanceStorage storage) {
		gameStage = storage.getStage();
		keyPrefs = new HashMap<KeyCode,String>();
		keyPrefsReversed = new HashMap<String,KeyCode>();
		engineMap = new HashMap<String,KeyCode>();
		gameInputs = new ArrayList<String>();
		
		String[] testInputs = {"left","right","up","down"}; //To be read in from data
		KeyCode[] engineInputs = {KeyCode.A,KeyCode.D,KeyCode.W,KeyCode.S}; //also defaults from data
		for(int i=0;i<testInputs.length;i++) {
			gameInputs.add(testInputs[i]);
			engineMap.put(testInputs[i],engineInputs[i]);
		}
		initializeInputs();
	}
	/**
	 * Method called to set the entity which the player can control
	 * for access by the key preferences.
	 * 
	 * @param gp
	 */
	public void setGamePlayer(Entity gp) {
		gamePlayer = gp;
		initializeInputs();
	}
	/**
	 * Method called to set a preference for a key.
	 * 
	 * @param input
	 * @param key
	 */
	public void setKey(String input,KeyCode key) {
		keyPrefs.put(key, input);
		keyPrefsReversed.put(input, key);
		addGameDirection(key,input);
	}
	/**
	 * Method called to change the key preferences for the actions in the game.
	 * 
	 * @param k
	 * @param input
	 */
	private void addGameDirection(KeyCode k, String input) {
		if(gamePlayer==null) {
			for(String s : gameInputs) {
				keyPrefs.put(engineMap.get(s), s);
				keyPrefsReversed.put(s,engineMap.get(s));
			}
			return;
		}
		if(input.equals("left")){
			gamePlayer.addComponent(new LeftKeyboardComponent(k.toString()));
		}
		else if(input.equals("right")) {
			gamePlayer.addComponent(new RightKeyboardComponent(k.toString()));
		}
		else if(input.equals("up")) {
			gamePlayer.addComponent(new UpKeyboardComponent(k.toString()));
		}
		else if(input.equals("down")) {
			gamePlayer.addComponent(new DownKeyboardComponent(k.toString()));
		}
	}
	/**
	 * Method called to get the input for a given key during gameplay.
	 * 
	 * @param key
	 * @return
	 */
	public String getInput(KeyCode key) {
		return keyPrefs.get(key);
	}
	
	/**
	 * Returns the Keycode set to a given input string.
	 * 
	 * @param input
	 * @return
	 */
	public KeyCode getKeyCode(String input) {
		return keyPrefsReversed.get(input);
	}
	
	/**
	 * Initialize the keys for all commands to be the default w-a-s-d.
	 */
	private void initializeInputs() {
		for(String s : gameInputs) {
			keyPrefs.put(engineMap.get(s), s);
			keyPrefsReversed.put(s,engineMap.get(s));
			addGameDirection(engineMap.get(s), s);
		}
	}
	/**
	 * Tracks the root node of the current game scene.
	 * 
	 * @param node
	 */
	public void setGameEngine(Engine e) {
		gameEngine = e;
	}
	
	/**
	 * Returns the current game engine.
	 * 
	 * @return
	 */
	public Engine getGameEngine(){
		return gameEngine;
	}
	
	/**
	 * Returns the current game title.
	 * 
	 * @return
	 */
	public String getGameTitle() {
		return gameTitle;
	}
	/**
	 * Sets the current game title.
	 * 
	 * @param gt
	 */
	public void setGameTitle(String gt) {
		this.gameTitle = gt;
	}
	
	/**
	 * Returns the list of inputs which the user
	 * can provide.
	 * 
	 * @return
	 */
	public List<String> getInputCommands(){
		return gameInputs;
	}
	
	/**
	 * Sets the current game stage.
	 * 
	 * @return
	 */
	public Stage getStage() {
		return this.gameStage;
	}
}
