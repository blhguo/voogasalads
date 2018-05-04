package game_engine;

import java.util.ArrayList;
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
	private List<KeyEvent> myKeyInputs;
	private List<Vector> myMouseInputs;

	public Engine() {
		myLevels = new HashMap<>();
		myCurrentLevel = 0;
		myIdCounter = 0;
		myKeyInputs = new LinkedList<>();
		myMouseInputs = new LinkedList<>();
		mySystems = new SystemInitializer().init(this);
	}

	public void update(double elapsedTime) {
		Level currentLevel = getLevel();
		for (GameSystem system : mySystems) {
			system.act(elapsedTime, currentLevel);
		}

		currentLevel.checkEvents();

		clearInput();
	}

	private void clearInput() {
		myKeyInputs.clear();
		myMouseInputs.clear();
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
		return getLevel(myCurrentLevel);
	}

	public Level getLevel(int levelId) {
		return myLevels.get(levelId);
	}

	public void setLevel(int dex) {
		myCurrentLevel = dex;
	}

	public <T> Map<Integer, List<Component<T>>> getLevelPreviews(List<Class<? extends Component<T>>> args) {
		Map<Integer, List<Component<T>>> preview = new HashMap<>();
		List<Component<T>> previewComponents;
		for (Map.Entry<Integer, Level> levelMapping : myLevels.entrySet()) {
			int id = levelMapping.getKey();
			Level level = levelMapping.getValue();
			previewComponents = new ArrayList<>();
			for (Class<? extends Component<T>> c : args) {
				previewComponents.add(level.getComponent(c));
			}
			preview.put(id, previewComponents);
		}
		return preview;
	}

	public List<KeyEvent> getKeyInputs(KeyCode keyInput) {
		return myKeyInputs.stream().filter(event -> keyInput.equals(event.getCode()))
				.collect(Collectors.toList());
	}

	public List<Vector> getMouseInputs() {
		return myMouseInputs;
	}

	public void receiveKeyInput(KeyEvent event) {
		myKeyInputs.add(event);
	}

	public void receiveMouseInput(Vector click) {
		myMouseInputs.add(click);
	}
}
