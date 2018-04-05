package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;

import game_engine.Engine;
import game_engine.System;
import game_engine.Entity;
import game_engine.components.Physics;
import game_engine.components.Position;

public class Movement extends System {
	private static final Class<? extends Component> POSITION = Position.class;

	public Movement(Engine engine) {
		super(engine);
	}

	// https://gamedev.stackexchange.com/questions/29617/how-to-make-a-character-jump
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, POSITION);
		for (Entity e : getEngine().getEntitiesContaining(args)) {
			Physics physics = (Physics) e.getComponent(PHYSICS);
			Position position = (Position) e.getComponent(POSITION);
<<<<<<< HEAD
			position.setX(calcPos(position.getX(), elapsedTime, physics.getXVel(), physics.getAccel()));
			position.setY(calcPos(position.getY(), elapsedTime, physics.getYVel(), physics.getAccel()));
=======
			position.setX(position.getX() + physics.getXVel() * elapsedTime);
			position.setY(position.getY() + physics.getYVel() * elapsedTime);
			physics.setYVel(physics.getYVel() + physics.getAccel() * elapsedTime);
>>>>>>> ba411d246320aae23f2e20dc06a41f02a664823f
		}
	}
}
