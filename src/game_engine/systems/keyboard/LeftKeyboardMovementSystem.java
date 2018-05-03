package game_engine.systems.keyboard;

import game_engine.Component;
import game_engine.Engine;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.XVelComponent;
import javafx.scene.input.KeyCode;

/**
 * 
 * @author Andy Nguyen, Kevin Deng, Jeremy Chen, Ben Hubsch
 * The purpose of this class is to handle keyboard movement in the leftwards direction
 *
 */
public class LeftKeyboardMovementSystem extends KeyboardMovementSystem{
	private static final Class<? extends Component<KeyCode>> LEFT_KEYBOARD_MOVE_INPUT = LeftKeyboardComponent.class;
	private static final Class<? extends Component<Double>> DEFAULT_X_VEL = DefaultXVelComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;
	private static final int DIRECTION = -1;
	
	/**
	 * instantiates a new LeftKeyboardMovementSystem with the reference to engine needed for reading inputs
	 * @param engine
	 */
	public LeftKeyboardMovementSystem(Engine engine) {
		super(engine, DIRECTION, LEFT_KEYBOARD_MOVE_INPUT, DEFAULT_X_VEL, X_VEL);
	}

}
