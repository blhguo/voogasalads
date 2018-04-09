package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.Vector;
import game_engine.components.KeyboardMoveLeftInputComponent;
import game_engine.components.KeyboardMoveRightInputComponent;
import game_engine.components.KeyboardMoveUpInputComponent;
import game_engine.components.KeyboardMoveDownInputComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import game_engine.GameSystem;
import game_engine.Input;

public class KeyboardMoveRightSystem extends GameSystem{
	private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	private static final Class<? extends Component> KEYBOARD_RIGHT = KeyboardMoveRightInputComponent.class;

	public KeyboardMoveRightSystem(Engine engine) {
		super(engine);
		
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, POSITION, KEYBOARD_RIGHT);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			for (InputEvent input : getEngine().getInputQueue()) {
				PhysicsComponent physics = (PhysicsComponent) entity.getComponent(PHYSICS);
				PositionComponent position = (PositionComponent) entity.getComponent(POSITION);
				KeyboardMoveRightInputComponent keyboardRight = (KeyboardMoveRightInputComponent) entity.getComponent(KEYBOARD_RIGHT);
				KeyEvent keyInput = (KeyEvent) input;
				if(keyboardRight.correctKey(keyInput.getCode())){
					Vector direction = keyboardRight.getDirection();
					position.setX(position.getX() + physics.getMaxXVel() * direction.getX());
					position.setY(position.getY() + physics.getMaxYVel() * direction.getY());
					getEngine().getInputQueue().remove(input);
				}
			}
		}
	}
}
