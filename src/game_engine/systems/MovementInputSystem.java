package game_engine.systems;

import game_engine.Component;
import game_engine.Engine;
import game_engine.GameSystem;
import game_engine.components.MovementInputComponent;

public class MovementInputSystem extends GameSystem {
	
	private static final Class<? extends Component> MOVEMENT_INPUT = MovementInputComponent.class;

	public MovementInputSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}
	
	

}
