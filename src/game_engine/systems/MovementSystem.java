package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;

import game_engine.Engine;
import game_engine.GameSystem;
import game_engine.Entity;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;

public class MovementSystem extends GameSystem {
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;

	public MovementSystem(Engine engine) {
		super(engine);
	}

	// https://gamedev.stackexchange.com/questions/29617/how-to-make-a-character-jump
	// http://jsfiddle.net/LyM87/3267/
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, POSITION);
		for (Entity e : getEngine().getEntitiesContaining(args)) {
			PhysicsComponent physics = (PhysicsComponent) e.getComponent(PHYSICS);
			PositionComponent position = (PositionComponent) e.getComponent(POSITION);

			
			position.setX(position.getX() + physics.getCurrXVel() * elapsedTime);
			position.setY(position.getY() + physics.getCurrYVel() * elapsedTime);
		
			//physics.setCurrXVel(physics.getCurrXVel() + physics.getAccel() * elapsedTime);
			physics.setCurrYVel(physics.getCurrYVel() + physics.getAccel() * elapsedTime);

		}
	}
}
