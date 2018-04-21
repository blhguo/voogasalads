package game_engine.systems.keyboard;

import game_engine.Component;
import game_engine.Engine;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.XVelComponent;
import javafx.scene.input.KeyCode;


public class LeftKeyboardMovementSystem extends KeyboardMovementSystem{
	private static final Class<? extends Component<KeyCode>> LEFT_KEYBOARD_MOVE_INPUT = LeftKeyboardComponent.class;
	private static final Class<? extends Component<Double>> DEFAULT_X_VEL = DefaultXVelComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	
	public LeftKeyboardMovementSystem(Engine engine) {
		super(engine, LEFT_KEYBOARD_MOVE_INPUT, DEFAULT_X_VEL, X_VEL);
	}

	@Override
	protected int getDirection() {
		return -1;
	}
}
