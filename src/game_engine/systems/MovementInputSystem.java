package game_engine.systems;

import game_engine.Component;
import game_engine.Engine;
import game_engine.System;
import game_engine.components.MovementInput;
import game_engine.components.Physics;
import game_engine.components.Position;

public class MovementInputSystem extends System{
	
	private static final Class<? extends Component> MOVEMENT_INPUT = MovementInput.class;

	public MovementInputSystem(Engine engine) {
		super(engine);
	}
	
	

}
