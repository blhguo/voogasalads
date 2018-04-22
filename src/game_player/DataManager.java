package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Entity;
import game_engine.components.keyboard.DownKeyboardComponent;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.keyboard.RightKeyboardComponent;
import game_engine.components.keyboard.UpKeyboardComponent;
import game_engine.level.Level;
import javafx.scene.input.KeyCode;

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
	private List<Level> gameLevels = new ArrayList<Level>();
	private Entity gamePlayer;
	
	public DataManager() {
		keyPrefs = new HashMap<KeyCode,String>();
		keyPrefsReversed = new HashMap<String,KeyCode>();
		engineMap = new HashMap<String,KeyCode>();
		gameInputs = new ArrayList<String>();
		
		String[] testInputs = {"left","right","up","down"}; //To be read in from data
		KeyCode[] engineInputs = {KeyCode.A,KeyCode.D,KeyCode.W,KeyCode.S};
		for(int i=0;i<testInputs.length;i++) {
			gameInputs.add(testInputs[i]);
			engineMap.put(testInputs[i],engineInputs[i]);
		}
		initializeInputs();
	}
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
	
	private void addGameDirection(KeyCode k, String input) {
		if(gamePlayer==null) {
			for(String s : gameInputs) {
				keyPrefs.put(engineMap.get(s), s);
				keyPrefsReversed.put(s,engineMap.get(s));
			}
			return;
		}
		if(input.equals("left")){
			gamePlayer.addComponent(new LeftKeyboardComponent(k));
		}
		else if(input.equals("right")) {
			gamePlayer.addComponent(new RightKeyboardComponent(k));
		}
		else if(input.equals("up")) {
			gamePlayer.addComponent(new UpKeyboardComponent(k));
		}
		else if(input.equals("down")) {
			gamePlayer.addComponent(new DownKeyboardComponent(k));
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
	
	public KeyCode getKeyCode(String input) {
		return keyPrefsReversed.get(input);
	}
	
	/**
	 * Initialize the keys for all commands to be enter.
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
	public void setGameLevels(List<Level> lev) {
		gameLevels = lev;
	}
	
	public List<Level> getGameLevels(){
		return gameLevels;
	}
	
	public List<String> getInputCommands(){
		return gameInputs;
	}
}
