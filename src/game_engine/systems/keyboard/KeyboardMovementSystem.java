package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.PrimeComponent;
import game_engine.level.Level;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Andy Nguyen, Kevin Deng, Ben Hubsch, Jeremy Chen
 *
 *         The purpose of this system class is to act on all entities that contain the specific
 *         physics and keyboard input components. This system loops over all keypresses taken from
 *         the input queue in engine and maps these keypresses to the corresponding movement within
 *         these respective Entities.
 */
public abstract class KeyboardMovementSystem implements GameSystem {

	private static Class<? extends Component<UUID>> PRIME = PrimeComponent.class;
	private static final EventType<KeyEvent> PRESSED = KeyEvent.KEY_PRESSED;
	private static final EventType<KeyEvent> RELEASED = KeyEvent.KEY_RELEASED;

	private Engine myEngine;
	private Class<? extends Component<KeyCode>> myKeyboardMoveInput;
	private Class<? extends Component<Double>> myDefaultVel;
	private Class<? extends Component<Double>> myVel;
	private int myDirection;

	public KeyboardMovementSystem(Engine engine, int direction, Class<? extends Component<KeyCode>> keyMoveInput,
			Class<? extends Component<Double>> defaultVel, Class<? extends Component<Double>> vel) {
		myEngine = engine;
		myDirection = direction;
		myKeyboardMoveInput = keyMoveInput;
		myDefaultVel = defaultVel;
		myVel = vel;
	}

	/**
	 * Listens for specific keyboard movement input from the engine and then moves entities
	 * correspondingly upon receiving the correct input
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(myKeyboardMoveInput, myDefaultVel, myVel, PRIME);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<KeyCode> keyInput = entity.getComponent(myKeyboardMoveInput);
			Component<Double> defaultVel = entity.getComponent(myDefaultVel);
			Component<Double> vel = entity.getComponent(myVel);
			for (KeyEvent input : myEngine.getKeyInputs(keyInput.getValue())) {
				if (input.getEventType().equals(PRESSED)) {
					vel.setValue(defaultVel.getValue() * myDirection);
				} else if (input.getEventType().equals(RELEASED)) {
					vel.setValue(0.0);
				}
			}
		}
	}

}