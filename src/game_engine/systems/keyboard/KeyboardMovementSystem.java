package game_engine.systems.keyboard;
import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Level;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
/*
 * @author Kevin Deng, Andy Nguyen, REFACTORED by Jeremy Chen
 */
public abstract class KeyboardMovementSystem extends GameSystem {
	private final Class<? extends Component<KeyCode>> KEYBOARD_MOVE_INPUT;
	private final Class<? extends Component<Double>> DEFAULT_VEL;
	private final Class<? extends Component<Double>> VEL;
	private static final String KEY_PRESSED = "KEY_PRESSED";
	private static final String KEY_RELEASED = "KEY_RELEASED";
	
	private Engine myEngine; 
	
	public KeyboardMovementSystem(Engine engine, Class<? extends Component<KeyCode>> keyMoveInput, Class<? extends Component<Double>> defaultVel, Class<? extends Component<Double>> vel) {
		myEngine = engine;
		KEYBOARD_MOVE_INPUT = keyMoveInput;
		DEFAULT_VEL = defaultVel;
		VEL = vel;
	}

	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(KEYBOARD_MOVE_INPUT, DEFAULT_VEL);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<KeyCode> keyInput = entity.getComponent(KEYBOARD_MOVE_INPUT);
			Component<Double> defaultVel = entity.getComponent(DEFAULT_VEL);
			Component<Double> vel = entity.getComponent(VEL);
			for (InputEvent input : myEngine.getInput(keyInput)) {
				if (input.getEventType().getName().equals(KEY_PRESSED)) {
					vel.setValue(defaultVel.getValue()*getDirection());
				} else if (input.getEventType().getName().equals(KEY_RELEASED)) {
					vel.setValue(0.0);
				}
			}
		}
	}
	
	protected abstract int getDirection();
}