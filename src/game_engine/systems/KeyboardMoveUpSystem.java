package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Vector;
import game_engine.components.KeyboardMoveDownInputComponent;
import game_engine.components.KeyboardMoveLeftInputComponent;
import game_engine.components.KeyboardMoveRightInputComponent;
import game_engine.components.KeyboardMoveUpInputComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

public class KeyboardMoveUpSystem extends GameSystem{
	private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	private static final Class<? extends Component> KEYBOARD_UP = KeyboardMoveUpInputComponent.class;
	public KeyboardMoveUpSystem(Engine engine) {
		super(engine);
		
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, POSITION, KEYBOARD_UP);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			for (InputEvent input : getEngine().getInputQueue()) {
				PhysicsComponent physics = (PhysicsComponent) entity.getComponent(PHYSICS);
				PositionComponent position = (PositionComponent) entity.getComponent(POSITION);
				KeyboardMoveUpInputComponent keyboardUp = (KeyboardMoveUpInputComponent) entity.getComponent(KEYBOARD_UP);
				KeyEvent keyInput = (KeyEvent) input;
				if(keyboardUp.correctKey(keyInput.getCode())){
					Vector direction = keyboardUp.getDirection();
					position.setX(position.getX() + physics.getMaxXVel() * direction.getX());
					position.setY(position.getY() + physics.getMaxYVel() * direction.getY());
					getEngine().getInputQueue().remove(input);
				}
			}
		}
	}

}
