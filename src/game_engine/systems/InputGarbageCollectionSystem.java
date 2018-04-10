package game_engine.systems;

import java.util.Queue;

import game_engine.Engine;
import game_engine.GameSystem;
import javafx.scene.input.InputEvent;

public class InputGarbageCollectionSystem extends GameSystem{

	public InputGarbageCollectionSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		Queue<InputEvent> inputs = getEngine().getInput();
		inputs.clear();
	}

}
