package gameplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class DataManager {
	private Map<KeyCode,String> keyPrefs;
	private List<String> gameInputs;
	private Node activeSceneRoot;
	
	public DataManager() {
		keyPrefs = new HashMap<KeyCode,String>();
		gameInputs = new ArrayList<String>();
		
		String[] testInputs = {"up","down","left","right"}; //To be read in from data
		for(int i=0;i<testInputs.length;i++) {
			gameInputs.add(testInputs[i]);
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
		System.out.print(keyPrefs.size());
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
	public void setNode(Node node) {
		activeSceneRoot = node;
	}
	
	public List<String> getInputCommands(){
		return gameInputs;
	}
}
