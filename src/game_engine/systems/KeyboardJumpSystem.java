package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Vector;
import game_engine.components.JumpComponent;
import game_engine.components.KeyboardJumpInputComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

public class KeyboardJumpSystem extends GameSystem{
	private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;
	private static final Class<? extends Component> KEYBOARD_JUMP_INPUT = KeyboardJumpInputComponent.class;
	private static final Class<? extends Component> JUMP = JumpComponent.class;
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	
	public KeyboardJumpSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, KEYBOARD_JUMP_INPUT, JUMP, POSITION);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			for(InputEvent input : getEngine().getInput()){
				KeyEvent key = (KeyEvent) getEngine().getInput().peek();
				PhysicsComponent physics = (PhysicsComponent) entity.getComponent(PHYSICS);
				PositionComponent pos = (PositionComponent) entity.getComponent(POSITION);				
				KeyboardJumpInputComponent jumpInput = (KeyboardJumpInputComponent) entity.getComponent(KEYBOARD_JUMP_INPUT);
				JumpComponent jump = (JumpComponent) entity.getComponent(JUMP);
				Vector direction = jumpInput.getDirection(key.getCode());
				if (direction.getY() == 1 && jump.getOnGround() && jump.getJumpsAllowed() != 0){
					pos.setY(pos.getY() + physics.getMaxYVel() * direction.getY() * elapsedTime);
					jump.setJumpsAllowed(jump.getJumpsAllowed() - 1);
					getEngine().getInput().remove(input);
				}
			}
		}
	}
}
