package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.level.Level;
import javafx.scene.input.KeyCode;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class DataManager {
	private Map<KeyCode,String> keyPrefs;
	private Map<String,KeyCode> engineMap;
	private List<String> gameInputs;
	private List<Level> gameLevels = new ArrayList<Level>();
	
	public DataManager() {
		keyPrefs = new HashMap<KeyCode,String>();
		engineMap = new HashMap<String,KeyCode>();
		gameInputs = new ArrayList<String>();
		
		String[] testInputs = {"left","right","up","down"}; //To be read in from data
		KeyCode[] engineInputs = {KeyCode.A,KeyCode.D,KeyCode.W,KeyCode.S};
		//wasd
		for(int i=0;i<testInputs.length;i++) {
			gameInputs.add(testInputs[i]);
			engineMap.put(testInputs[i],engineInputs[i]);
		}
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
	 * Method called to get the correct KeyCode for a given key during gameplay.
	 * 
	 * @param key
	 * @return
	 */
	public KeyCode getKeyCodeInput(KeyCode key) {
		return engineMap.get(keyPrefs.get(key));
	}
	
	/**
	 * Initialize the keys for all commands to be enter.
	 */
	private void initializeInputs() {
		for(String s : gameInputs) {
			keyPrefs.put(KeyCode.ENTER, s);
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
