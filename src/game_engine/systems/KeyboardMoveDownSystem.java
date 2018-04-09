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

public class KeyboardMoveDownSystem extends GameSystem{
	private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	private static final Class<? extends Component> KEYBOARD_DOWN = KeyboardMoveDownInputComponent.class;
	public KeyboardMoveDownSystem(Engine engine) {
		super(engine);
		
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, POSITION, KEYBOARD_DOWN);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			for (InputEvent input : getEngine().getInputQueue()) {
				PhysicsComponent physics = (PhysicsComponent) entity.getComponent(PHYSICS);
				PositionComponent position = (PositionComponent) entity.getComponent(POSITION);
				KeyboardMoveDownInputComponent keyboardDown = (KeyboardMoveDownInputComponent) entity.getComponent(KEYBOARD_DOWN);
				KeyEvent keyInput = (KeyEvent) input;
				if(keyboardDown.correctKey(keyInput.getCode())){
					Vector direction = keyboardDown.getDirection();
					position.setX(position.getX() + physics.getMaxXVel() * direction.getX());
					position.setY(position.getY() + physics.getMaxYVel() * direction.getY());
					getEngine().getInputQueue().remove(input);
				}
			}
		}
	}

}
