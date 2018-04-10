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
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

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
			for(InputEvent input : getEngine().getInput()){
				KeyEvent key = (KeyEvent) getEngine().getInput().peek();
				PhysicsComponent physics = (PhysicsComponent) entity.getComponent(PHYSICS);		
				KeyboardJumpInputComponent jumpInput = (KeyboardJumpInputComponent) entity.getComponent(KEYBOARD_JUMP_INPUT);
				JumpComponent jump = (JumpComponent) entity.getComponent(JUMP);
				Vector direction = jumpInput.getDirection(key.getCode());
				if (direction.getY() == 1 && jump.getJumpsAllowed() != 0){
					System.out.println(input.getEventType().getName());
					physics.setCurrYVel(jump.getJumpVelocity());
					jump.setJumpsAllowed(jump.getJumpsAllowed() - 1);
					// should fix this to be more active and not give up data
					getEngine().getInput().remove(input);
				}
			}
		}
	}
}
