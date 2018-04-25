package game_engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import game_engine.level.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import voogasalad.util.reflection.Reflection;

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
		mySystems = initSystems();
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

	public List<KeyEvent> getInput(Component<KeyCode> keyInput) {
		return myInputs.stream().filter(keyEvent -> keyInput.getValue().equals(keyEvent.getCode()))
				.collect(Collectors.toList());
	}

	public void receiveInput(KeyEvent event) {
		myInputs.add(event);
	}

	private List<GameSystem> initSystems() {
		Reflections reflections = new Reflections("game_engine", new SubTypesScanner(true));
		Set<Class<? extends GameSystem>> allClasses = reflections.getSubTypesOf(GameSystem.class);

		List<GameSystem> systems = new ArrayList<>();

		allClasses.stream().filter(clazz -> !Modifier.isAbstract(clazz.getModifiers())).forEach(clazz -> {
			try {
				Parameter[] params = clazz.getDeclaredConstructors()[0].getParameters();
				GameSystem system;
				if (params.length > 0) {
					system = (GameSystem) Reflection.createInstance(clazz.getName(), this);
				} else {
					system = (GameSystem) Reflection.createInstance(clazz.getName());
				}
				systems.add(system);
			} catch (Exception e) {
				// Do nothing: Continue without this system.
				// No use in adding a NullSystem or anything like that since the user doesn't see it.
			}
		});

		return systems;
	}

}
