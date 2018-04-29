package game_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import game_engine.level.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Engine {

	private Map<Integer, Level> myLevels;
	private int myCurrentLevel;
	private int myIdCounter;
	private List<GameSystem> mySystems;
	private List<Tuple<UUID, KeyEvent>> myKeyInputs;
	private List<Tuple<UUID, Vector>> myMouseInputs;

	public Engine() {
		myLevels = new HashMap<Integer, Level>();
		myCurrentLevel = 0;
		myIdCounter = 0;
		myKeyInputs = new LinkedList<>();
		myMouseInputs = new LinkedList<>();
		mySystems = new SystemInitializer().init(this);
		System.out.println(mySystems.size());
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
		return myLevels.get(myCurrentLevel);
	}

	public Level getLevel(int levelId) {
		return myLevels.get(levelId);
	}

	public void setLevel(int dex) {
		myCurrentLevel = dex;
	}

	public Map<Integer, List<Component<?>>> getLevelPreviews(List<Class<? extends Component<?>>> args) {
		Map<Integer, List<Component<?>>> preview = new HashMap<Integer, List<Component<?>>>();
		List<Component<?>> previewComponents;
		for (Integer key : myLevels.keySet()) {
			previewComponents = new ArrayList<Component<?>>();
			Level lvl = myLevels.get(key);
			for (Class<? extends Component<?>> c : args) {
				previewComponents.add(lvl.getComponent(c));
			}
			preview.put(key, previewComponents);
		}
		return preview;
	}

	public List<Tuple<UUID, KeyEvent>> getKeyInputs(KeyCode keyInput) {
		return myKeyInputs.stream().filter(keyTuple -> keyInput.equals(keyTuple.getSecond().getCode()))
				.collect(Collectors.toList());
	}

	public List<Tuple<UUID, Vector>> getMouseInputs() {
		return myMouseInputs;
	}

	public void receiveKeyInput(Tuple<UUID, KeyEvent> event) {
		myKeyInputs.add(event);
	}

	public void receiveMouseInput(Tuple<UUID, Vector> click) {
		myMouseInputs.add(click);
	}
}
