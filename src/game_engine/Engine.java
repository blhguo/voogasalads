package game_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import game_engine.level.Level;
import game_engine.systems.DespawnSystem;
import game_engine.systems.HealthSystem;
import game_engine.systems.PositionSystem;
import game_engine.systems.ProjectileDespawnSystem;
import game_engine.systems.ProjectileSpawnSystem;
import game_engine.systems.VelocitySystem;
import game_engine.systems.collision.CollisionBroadSystem;
import game_engine.systems.collision.CollisionResponseSystem;
import game_engine.systems.keyboard.DownKeyboardMovementSystem;
import game_engine.systems.keyboard.KeyboardJumpSystem;
import game_engine.systems.keyboard.LeftKeyboardMovementSystem;
import game_engine.systems.keyboard.RightKeyboardMovementSystem;
import game_engine.systems.keyboard.UpKeyboardMovementSystem;
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
		
		
		//TEMP!
		mySystems = new ArrayList<>();
		mySystems.add(new CollisionResponseSystem());
		mySystems.add(new KeyboardJumpSystem(this));
		mySystems.add(new CollisionBroadSystem());
		mySystems.add(new PositionSystem());
		mySystems.add(new VelocitySystem());
		mySystems.add(new LeftKeyboardMovementSystem(this));
		mySystems.add(new RightKeyboardMovementSystem(this));
		mySystems.add(new UpKeyboardMovementSystem(this));
		mySystems.add(new DownKeyboardMovementSystem(this));
		mySystems.add(new HealthSystem());
		mySystems.add(new ProjectileSpawnSystem(this));
		//mySystems.add( new ProjectileDespawnSystem());
		//mySystems.add(new DespawnSystem());
	}

	public void update(double elapsedTime) {
		System.out.println("Getting called!");
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
		System.out.println("added input!");
		myInputs.add(event);
	}
}
