package game_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import game_engine.level.Level;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Engine {
	private Map<Integer, Level> myLevels;
	private int myCurrentLevel;
	private int myIdCounter;
	private List<GameSystem> mySystems;
	private LinkedList<InputEvent> myInputs;

	public Engine() {
		myLevels = new HashMap<Integer, Level>();
		myCurrentLevel = 0;
		myIdCounter = 0;
		myInputs = new LinkedList<>();
		mySystems = new SystemInitializer().init(this);

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

	public List<KeyEvent> getInput(KeyCode keyInput) {
		return myInputs.stream().filter((KeyEvent)keyEvent -> keyInput.equals((KeyEvent)keyEvent.getCode()))
				.collect(Collectors.toList());
	}
	
	public List<MouseEvent> getInput(MouseEvent mouseInput){
		return 
	}

	public void receiveInput(KeyEvent event) {
		myInputs.add(event);
	}
}
