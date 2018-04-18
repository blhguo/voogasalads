package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Vector;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import game_engine.components.keyboard.LeftKeyboardMovementInputComponent;
import game_engine.components.physics.DefaultXVelocityComponent;
import game_engine.components.physics.XVelocityComponent;


public class LeftKeyboardMovementSystem extends GameSystem{
	private static final Class<? extends Component> LEFT_KEYBOARD_MOVE_INPUT = LeftKeyboardMovementInputComponent.class;
	private static final Class<? extends Component> DEFAULT_X_VEL = DefaultXVelocityComponent.class;
	private static final Class<? extends Component> X_VEL = XVelocityComponent.class;
	private static final String KEY_PRESSED = "KEY_PRESSED";
	private static final String KEY_RELEASED = "KEY_RELEASED";
	
	public LeftKeyboardMovementSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(LEFT_KEYBOARD_MOVE_INPUT, DEFAULT_X_VEL);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			Component leftKeyInput = entity.getComponent(LEFT_KEYBOARD_MOVE_INPUT);
			Component defaultXVel = entity.getComponent(DEFAULT_X_VEL);
			Component xVel = entity.getComponent(X_VEL);
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
					xVel.setValue(defaultXVel.getValue());
				} else if (input.getEventType().getName().equals(KEY_RELEASED) && correctKey) {
					// add horiz vector to MovementRequests
					xVel.setValue("0");
				}
			}
		}
	}

}
