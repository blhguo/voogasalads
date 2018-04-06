package GameEngine.Systems;

import GameEngine.Component;
import GameEngine.Engine;
import GameEngine.Entity;
import GameEngine.System;
import GameEngine.Components.Physics;

public class MovementInputSystem extends System {
	
	private static final Class<? extends Component> MOVEMENT_INPUT = KeyboardMovementInput.class;

	
	public MovementInputSystem(Engine engine) {
		super(engine);
	}

	
	@Override
	public void act(double elapsedTime) {
		for (Entity e : getEngine().getEntitiesContaining(MOVEMENT_INPUT)) {
			KeyboardMovementInput movementInput = (KeyboardMovementInput) e.getComponent(MOVEMENT_INPUT);
			
		}
		
	}

}
