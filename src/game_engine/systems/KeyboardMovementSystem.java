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
				KeyEvent keyInput = (KeyEvent) input;
				Vector direction = keyboardInput.getDirection(keyInput.getCode());
				position.setX(position.getX() + physics.getCurrXVel() * direction.getX());
				physics.setCurrXVel(direction.getX() * physics.getCurrXVel());
				getEngine().getInput().remove(input);
			}
		}
	}
}
