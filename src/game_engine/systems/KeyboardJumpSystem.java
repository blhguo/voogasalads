package game_engine.systems;

import java.util.Arrays;

import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Input;
import game_engine.Vector;
import game_engine.components.KeyboardMovementInputComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.KeyboardJumpInputComponent;
import game_engine.components.CollidedComponent;
import game_engine.components.JumpComponent;

public class KeyboardJumpSystem extends GameSystem{
	private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;
	private static final Class<? extends Component> KEYBOARD_JUMP_INPUT = KeyboardJumpInputComponent.class;
	private static final Class<? extends Component> JUMP = JumpComponent.class;
	
	public KeyboardJumpSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, KEYBOARD_JUMP_INPUT, JUMP);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			for (Input input : getEngine().getInput()) {
				PhysicsComponent physics = (PhysicsComponent) entity.getComponent(PHYSICS);
				KeyboardJumpInputComponent jumpInput = (KeyboardJumpInputComponent) entity.getComponent(KEYBOARD_JUMP_INPUT);
				JumpComponent jump = (JumpComponent) entity.getComponent(JUMP);
				
				Vector direction = jumpInput.getDirection(null); //TODO:figure out how inputs are passed. should be keycode, but currently is string from getInput()
				if (jump.getOnGround() && jump.getJumpsAllowed() != 0){
					//TODO: this won't always work, maybe include a conditional of valid code because an invalid
					//code will result in us setting the current y vel to 0...talk with members
					physics.setCurrYVel(physics.getMaxYVel()* direction.getY());
				}
			}
		}
	}
}
