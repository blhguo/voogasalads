package game_engine.systems.keyboard;
import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.level.Level;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
/*
 * @author Kevin Deng, Andy Nguyen, REFACTORED by Jeremy Chen
 */
public abstract class KeyboardMovementSystem extends GameSystem {
	private Class<? extends Component<KeyCode>> myKeyboardMoveInput;
	private Class<? extends Component<Double>> myDefaultVel;
	private Class<? extends Component<Double>> myVel;
	
	private static final String KEY_PRESSED = "KEY_PRESSED";
	private static final String KEY_RELEASED = "KEY_RELEASED";
	
	private Engine myEngine; 
	
	public KeyboardMovementSystem(Engine engine, Class<? extends Component<KeyCode>> keyMoveInput, Class<? extends Component<Double>> defaultVel, Class<? extends Component<Double>> vel) {
		myEngine = engine;
		myKeyboardMoveInput = keyMoveInput;
		myDefaultVel = defaultVel;
		myVel = vel;
	}

	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(myKeyboardMoveInput, myDefaultVel, myVel);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<KeyCode> keyInput = entity.getComponent(myKeyboardMoveInput);
			Component<Double> defaultVel = entity.getComponent(myDefaultVel);
			Component<Double> vel = entity.getComponent(myVel);
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