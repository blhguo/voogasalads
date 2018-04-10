package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Vector;
import game_engine.components.KeyboardMovementInputComponent;
import game_engine.components.physics.XPhysicsComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

public class KeyboardMovementSystem extends GameSystem{

	private static final Class<? extends Component> KEYBOARD_MOVE_INPUT = KeyboardMovementInputComponent.class;
	private static final Class<? extends Component> HORIZONTAL_PHYSICS = XPhysicsComponent.class;
	private static final String KEY_PRESSED = "KEY_PRESSED";
	private static final String KEY_RELEASED = "KEY_RELEASED";

	public KeyboardMovementSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(HORIZONTAL_PHYSICS, KEYBOARD_MOVE_INPUT);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			for (InputEvent input : getEngine().getInput()) {
				XPhysicsComponent horizontal = (XPhysicsComponent) entity.getComponent(HORIZONTAL_PHYSICS);
				KeyboardMovementInputComponent keyboardInput = (KeyboardMovementInputComponent) entity.getComponent(KEYBOARD_MOVE_INPUT);
				KeyEvent keyInput = (KeyEvent) input;
				Vector direction = keyboardInput.getDirection(keyInput.getCode());
				if (direction.getX() != 0) {
					horizontal.setCurrVel(direction.getX() * horizontal.getCurrVel());
					getEngine().getInput().remove(input);
				}
				
				if (input.getEventType().getName().equals(KEY_PRESSED)) {
					System.out.println("here!: " + input.getEventType().getName());
					System.out.println("default!: " + horizontal.getDefaultVel());
					System.out.println("dir!: " + horizontal.getDefaultVel());
					horizontal.setCurrVel(direction.getX() * horizontal.getDefaultVel());
				} else if (input.getEventType().getName().equals(KEY_RELEASED)) {
					horizontal.setCurrVel(0);
				}
			}
		}
	}
}
