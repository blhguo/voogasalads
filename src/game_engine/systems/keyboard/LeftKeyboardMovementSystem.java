package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.XVelComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class LeftKeyboardMovementSystem extends KeyboardMovementSystem{
	private static final Class<? extends Component<KeyCode>> LEFT_KEYBOARD_MOVE_INPUT = LeftKeyboardComponent.class;
	private static final Class<? extends Component<Double>> DEFAULT_X_VEL = DefaultXVelComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	
	public LeftKeyboardMovementSystem(Engine engine) {
		super(engine, LEFT_KEYBOARD_MOVE_INPUT, DEFAULT_X_VEL, X_VEL);
	}

	@Override
	protected int getDirection() {
		return 1;
	}
}
