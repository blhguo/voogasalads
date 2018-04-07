package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.Vector;
import game_engine.components.KeyboardMovementInput;
import game_engine.components.Physics;

import game_engine.GameSystem;
import game_engine.Input;

public class KeyboardSystem extends GameSystem{
	private static final Class<? extends Component> PHYSICS = Physics.class;
	private static final Class<? extends Component> KEYBOARD_MOVE_INPUT = KeyboardMovementInput.class;

	public KeyboardSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, KEYBOARD_MOVE_INPUT);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			for (Input input : getEngine().getInput()) {
				Physics physics = (Physics) entity.getComponent(PHYSICS);
				KeyboardMovementInput keyboardInput = (KeyboardMovementInput) entity.getComponent(KEYBOARD_MOVE_INPUT);
				Vector direction = keyboardInput.getDirection(input.getInput());
				physics.setXVel(direction.getX() * keyboardInput.getOffset());
				physics.setYVel(direction.getY() * keyboardInput.getOffset());
			}
		}
	}
}
