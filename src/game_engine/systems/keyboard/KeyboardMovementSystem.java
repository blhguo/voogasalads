package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Vector;
import game_engine.components.keyboard.KeyboardMovementInputComponent;
import game_engine.components.physics.XPhysicsComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * The purpose of this system class is to act on all entities that contain the XPhysics and KeyboardMovementInput Components.
 * This system loops over all keypresses taken from the input queue in engine and maps these keypresses to potential horizontal movement
 * within these respective Entities.
 *
 */
public class KeyboardMovementSystem extends GameSystem{

	private static final Class<? extends Component> KEYBOARD_MOVE_INPUT = KeyboardMovementInputComponent.class;
	private static final Class<? extends Component> HORIZONTAL_PHYSICS = XPhysicsComponent.class;
	private static final String KEY_PRESSED = "KEY_PRESSED";
	private static final String KEY_RELEASED = "KEY_RELEASED";

	/**
	 * Creates a new instance of the KeyboardMovementSystem
	 * @param engine
	 */
	public KeyboardMovementSystem(Engine engine) {
		super(engine);
	}

	/**
	 * This method loops over all entities that contain the correct components necessary for horizontal movement through keyboard input.
	 * It then acts on these entities by changing the x velocity of the XPhysics component of these entities whenever a valid movement input
	 * keypress is read in from the input queue in Engine.
	 */
	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(HORIZONTAL_PHYSICS, KEYBOARD_MOVE_INPUT);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			XPhysicsComponent horizontal = (XPhysicsComponent) entity.getComponent(HORIZONTAL_PHYSICS);
			KeyboardMovementInputComponent keyboardInput = (KeyboardMovementInputComponent) entity.getComponent(KEYBOARD_MOVE_INPUT);
			for (InputEvent input : getEngine().getInput()) {
				KeyEvent keyInput = (KeyEvent) input;
				Vector direction = keyboardInput.getDirection(keyInput.getCode());
				if (direction.getX() != 0) {
					// add horiz vector to MovementRequests
					horizontal.setCurrVel(direction.getX() * horizontal.getCurrVel());
				}
				if (input.getEventType().getName().equals(KEY_PRESSED) && direction.getX() != 0) {
					// add horiz vector to MovementRequests
					horizontal.setCurrVel(direction.getX() * horizontal.getDefaultVel());
				} else if (input.getEventType().getName().equals(KEY_RELEASED)) {
					// add horiz vector to MovementRequests
					horizontal.setCurrVel(0);
				}
			}
		}
	}
}
