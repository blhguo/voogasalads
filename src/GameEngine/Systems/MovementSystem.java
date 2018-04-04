package GameEngine.Systems;

import GameEngine.Component;
import GameEngine.Entity;
import GameEngine.System;
import GameEngine.Components.Position;

public class MovementSystem extends System {
	private static final Class<Physics> PHYSICS = Physics.class;
	private static final Class<? extends Component> POSITION = Position.class;
	private static final double ONE_HALF = 0.5;
	
	public MovementSystem(Engine engine) {
		super(engine);
	}

	public void act(double elapsedTime, Engine engine) {
		for (Entity e : getEngine().getEntitiesWith(PHYSICS, POSITION)) {
			Physics physics = (Physics) e.getComponent(PHYSICS);
			Position position = (Position) e.getComponent(POSITION);
			position.setX(calcPos(position.getX(), elapsedTime, physics.getXVel(), physics.getAccel()));
			position.setX(calcPos(position.getY(), elapsedTime, physics.getYVel(), physics.getAccel()));
		}
	}
	
	private double calcPos(double pos, double time, double vel, double accel) {
		return pos + time * vel + ONE_HALF * accel * Math.pow(time, 2);
	}
}
