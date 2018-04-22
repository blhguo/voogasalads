package game_engine.systems.keyboard;

import game_engine.Component;
import game_engine.Engine;
import game_engine.components.keyboard.UpKeyboardComponent;
import game_engine.components.physics.DefaultYVelComponent;
import game_engine.components.physics.YVelComponent;
import javafx.scene.input.KeyCode;


public class UpKeyboardMovementSystem extends KeyboardMovementSystem {
	private static final Class<? extends Component<KeyCode>> UP_KEYBOARD_MOVE_INPUT = UpKeyboardComponent.class;
	private static final Class<? extends Component<Double>> DEFAULT_Y_VEL = DefaultYVelComponent.class;
	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;
	
	public UpKeyboardMovementSystem(Engine engine) {
		super(engine, UP_KEYBOARD_MOVE_INPUT, DEFAULT_Y_VEL, Y_VEL);
	}

	@Override
	protected int getDirection() {
		return -1;
	}
}