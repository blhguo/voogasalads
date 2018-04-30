package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Tuple;
import game_engine.level.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/*
 * @author Kevin Deng, Andy Nguyen, REFACTORED by Jeremy Chen
 */
public abstract class KeyboardMovementSystem implements GameSystem {

	private static final String KEY_PRESSED = "KEY_PRESSED";
	private static final String KEY_RELEASED = "KEY_RELEASED";

	private Engine myEngine;
	private Class<? extends Component<KeyCode>> myKeyboardMoveInput;
	private Class<? extends Component<Double>> myDefaultVel;
	private Class<? extends Component<Double>> myVel;
	private Class<? extends Component<UUID>> myPrime;
	private int myDirection;

	public KeyboardMovementSystem(Engine engine, int direction, Class<? extends Component<KeyCode>> keyMoveInput,
			Class<? extends Component<Double>> defaultVel, Class<? extends Component<Double>> vel,
			Class<? extends Component<UUID>> prime) {
		myEngine = engine;
		myDirection = direction;
		myKeyboardMoveInput = keyMoveInput;
		myDefaultVel = defaultVel;
		myVel = vel;
		myPrime = prime;
	}

	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(myKeyboardMoveInput, myDefaultVel, myVel, myPrime);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<KeyCode> keyInput = entity.getComponent(myKeyboardMoveInput);
			Component<Double> defaultVel = entity.getComponent(myDefaultVel);
			Component<Double> vel = entity.getComponent(myVel);
			Component<UUID> prime = entity.getComponent(myPrime);
			for (Tuple<UUID, KeyEvent> input : myEngine.getKeyInputs(keyInput.getValue())) {	
				if (!checkEquals(prime.getValue(), input.getFirst())) {
					continue;
				}
				
				if (input.getSecond().getEventType().getName().equals(KEY_PRESSED)) {
					vel.setValue(defaultVel.getValue() * myDirection);
				} else if (input.getSecond().getEventType().getName().equals(KEY_RELEASED)) {
					vel.setValue(0.0);
				}
			}
		}
	}
	
	private boolean checkEquals(UUID first, UUID second) {
		return first.equals(second);
	}
}