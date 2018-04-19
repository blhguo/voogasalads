package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.keyboard.RightKeyboardMovementInputComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.XVelComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;


public class RightKeyboardMovementSystem extends GameSystem{
	private static final Class<? extends Component<String>> RIGHT_KEYBOARD_MOVE_INPUT = RightKeyboardMovementInputComponent.class;
	private static final Class<? extends Component<Double>> DEFAULT_X_VEL = DefaultXVelComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	private static final String KEY_PRESSED = "KEY_PRESSED";
	private static final String KEY_RELEASED = "KEY_RELEASED";
	
	public RightKeyboardMovementSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component<?>>> args = Arrays.asList(RIGHT_KEYBOARD_MOVE_INPUT, DEFAULT_X_VEL);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			Component<String> leftKeyInput = entity.getComponent(RIGHT_KEYBOARD_MOVE_INPUT);
			Component<Double> defaultXVel = entity.getComponent(DEFAULT_X_VEL);
			Component<Double> xVel = entity.getComponent(X_VEL);
			for (InputEvent input : getEngine().getInput()) {
				KeyEvent key = (KeyEvent) input;
				boolean correctKey = leftKeyInput.getValue().equals(key.getCode());
				/*
				if (correctKey) {
					// add horiz vector to MovementRequests
					horizontal.setCurrVel(direction.getX() * horizontal.getCurrVel());
				}
				*/
				if (input.getEventType().getName().equals(KEY_PRESSED) && correctKey) {
					// add horiz vector to MovementRequests
					xVel.setValue(defaultXVel.getValue()*-1);
				} else if (input.getEventType().getName().equals(KEY_RELEASED) && correctKey) {
					// add horiz vector to MovementRequests
					xVel.setValue(0.0);
				}
			}
		}
	}

}