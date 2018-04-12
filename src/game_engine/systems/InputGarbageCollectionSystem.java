package game_engine.systems;

import java.util.List;

import game_engine.Engine;
import game_engine.GameSystem;
import javafx.scene.input.InputEvent;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * The purpose of this system class is to clear all unused inputs at the end of every iteration of the game loop
 * in order to get rid of unneccesary memory storage that could potentially slow down the game.
 *
 */
public class InputGarbageCollectionSystem extends GameSystem{

	/**
	 * Create a new instance of InputGarbageCollectionSystem
	 * @param engine
	 */
	public InputGarbageCollectionSystem(Engine engine) {
		super(engine);
	}

	/**
	 * Gets all of the inputs from the engine in which this system belongs and clears all of them
	 */
	@Override
	public void act(double elapsedTime) {
		List<InputEvent> inputs = getEngine().getInput();
		inputs.clear();
	}

}
