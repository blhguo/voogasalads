package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.keyboard.DownKeyboardComponent;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.keyboard.UpKeyboardComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.DefaultYVelComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class DownKeyboardMovementSystem extends KeyboardMovementSystem{
	private static final Class<? extends Component<KeyCode>> DOWN_KEYBOARD_MOVE_INPUT = DownKeyboardComponent.class;
	private static final Class<? extends Component<Double>> DEFAULT_Y_VEL = DefaultYVelComponent.class;
	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;
	
	public DownKeyboardMovementSystem(Engine engine) {
		super(engine, DOWN_KEYBOARD_MOVE_INPUT, DEFAULT_Y_VEL, Y_VEL);
	}

	@Override
	protected int getDirection() {
		return -1;
	}
}
