package game_engine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import game_engine.level.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Engine {
	private Map<Integer, Level> myLevels;
	private int myCurrentLevel;
	private int myIdCounter;
	private List<GameSystem> mySystems;
	private LinkedList<KeyEvent> myInputs;

	public Engine() {
		myLevels = new HashMap<Integer, Level>();
		myCurrentLevel = 0;
		myIdCounter = 0;
		myInputs = new LinkedList<KeyEvent>();
	}

	public void update(double elapsedTime) {
		Level currentLevel = getLevel();
		for (GameSystem system : mySystems) {
			system.act(elapsedTime, currentLevel);
		}

		currentLevel.checkEvents();

		myInputs.clear();
	}

	public Level createLevel() {
		Level createdLevel = new Level(myIdCounter);
		myLevels.put(myIdCounter, createdLevel);
		myIdCounter++;
		return createdLevel;
	}

	public void removeLevel(int id) {
		myLevels.remove(id);
	}

	public Level getLevel() {
		return myLevels.get(myCurrentLevel);
	}

	public void setLevel(int dex) {
		myCurrentLevel = dex;
	}

	public List<KeyEvent> getInput(Component<KeyCode> keyInput) {
		return myInputs.stream().filter(keyEvent -> keyInput.getValue().equals(keyEvent.getCode())).collect(Collectors.toList());
	}

	public void receiveInput(KeyEvent event) {
		myInputs.add(event);
	}

	// WROTE FOR TEMPORARY TESTING -- REMOVE LATER!!
	public void clearInputs() {
		myInputs.clear();
	}

	public List<Entity> getDespawned() {
		// TODO Auto-generated method stub
		return null;
	}

}
