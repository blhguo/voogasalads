package game_engine.systems.keyboard;

import game_engine.Component;
import game_engine.Engine;
import game_engine.components.keyboard.RightKeyboardComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.XVelComponent;
import javafx.scene.input.KeyCode;


public class RightKeyboardMovementSystem extends KeyboardMovementSystem{
	private static final Class<? extends Component<KeyCode>> RIGHT_KEYBOARD_MOVE_INPUT = RightKeyboardComponent.class;
	private static final Class<? extends Component<Double>> DEFAULT_X_VEL = DefaultXVelComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	
	public RightKeyboardMovementSystem(Engine engine) {
		super(engine, RIGHT_KEYBOARD_MOVE_INPUT, DEFAULT_X_VEL, X_VEL);
	}

	@Override
	protected int getDirection() {
		return 1;
	}
}