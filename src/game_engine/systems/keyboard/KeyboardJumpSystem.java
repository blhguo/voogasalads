package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.Vector;
import game_engine.components.JumpComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.components.physics.YPhysicsComponent;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

public class KeyboardJumpSystem extends GameSystem{
	
	private static final Class<? extends Component> VERTICAL_PHYSICS = YPhysicsComponent.class;
	private static final Class<? extends Component> KEYBOARD_JUMP_INPUT = KeyboardJumpInputComponent.class;
	private static final Class<? extends Component> JUMP = JumpComponent.class;
	private static final String KEY_PRESSED = "KEY_PRESSED";
	
	public KeyboardJumpSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(VERTICAL_PHYSICS, KEYBOARD_JUMP_INPUT, JUMP);
		for (Entity entity : getEngine().getEntitiesContaining(args)) {
			YPhysicsComponent yPhysics = (YPhysicsComponent) entity.getComponent(VERTICAL_PHYSICS);		
			KeyboardJumpInputComponent jumpInput = (KeyboardJumpInputComponent) entity.getComponent(KEYBOARD_JUMP_INPUT);
			JumpComponent jump = (JumpComponent) entity.getComponent(JUMP);
			for(InputEvent input : getEngine().getInput()){
				if (!input.getEventType().getName().equals(KEY_PRESSED)) {
					continue;
				}
				KeyEvent key = (KeyEvent) getEngine().getInput().get(0);
				Vector direction = jumpInput.getDirection(key.getCode());
				if (direction.getY() == 1 && jump.getJumpsAllowed() != 0){
					yPhysics.setCurrVel(yPhysics.getDefaultVel());
					jump.setJumpsAllowed(jump.getJumpsAllowed() - 1);
				}
			}
		}
	}
}
