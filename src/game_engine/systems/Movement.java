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
	private static final Class<? extends Component> PHYSICS = Physics.class;
	private static final Class<? extends Component> POSITION = Position.class;
	private static final double ONE_HALF = 0.5;
	private static final double TWO = 2;
	
	public Movement(Engine engine) {
		super(engine);
	}

	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, POSITION);
		for (Entity e : getEngine().getEntitiesContaining(args)) {
			Physics physics = (Physics) e.getComponent(PHYSICS);
			Position position = (Position) e.getComponent(POSITION);
			position.setX(calcPos(position.getX(), elapsedTime, physics.getXVel(), physics.getAccel()));
			position.setX(calcPos(position.getY(), elapsedTime, physics.getYVel(), physics.getAccel()));
		}
	}
	
	private double calcPos(double pos, double time, double vel, double accel) {
		return pos + time * vel + ONE_HALF * accel * Math.pow(time, TWO);
	}
}
