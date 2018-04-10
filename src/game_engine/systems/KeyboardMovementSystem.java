package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Vector;
import game_engine.components.KeyboardMovementInputComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

public class KeyboardMovementSystem extends GameSystem{
	private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	private static final Class<? extends Component> KEYBOARD_MOVE_INPUT = KeyboardMovementInputComponent.class;

	public KeyboardMovementSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(POSITION, KEYBOARD_MOVE_INPUT);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			for (InputEvent input : getEngine().getInput()) {
				PhysicsComponent physics = (PhysicsComponent) entity.getComponent(PHYSICS);
				PositionComponent position = (PositionComponent) entity.getComponent(POSITION);
				KeyboardMovementInputComponent keyboardInput = (KeyboardMovementInputComponent) entity.getComponent(KEYBOARD_MOVE_INPUT);
				//TODO:make use of input to extract keycode once Ben is done with input
				//obviously don't want to input null in the param
				KeyEvent keyInput = (KeyEvent) input;
				Vector direction = keyboardInput.getDirection(keyInput.getCode());
				position.setX(position.getX() + physics.getMaxXVel() * direction.getX());
				
				getEngine().getInput().remove(input);
				physics.setCurrXVel(direction.getX() * physics.getCurrXVel());
				
				// If I'm jumping and on the ground, set y-velocity = max
				if (direction.getY() == 1 /* && I am on the ground */) {
					physics.setCurrYVel(physics.getCurrYVel());
				}
				physics.setCurrXVel(direction.getX() * physics.getMaxXVel());
			}
		}
	}
}
