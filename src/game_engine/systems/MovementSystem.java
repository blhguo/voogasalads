package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.PositionComponent;
import game_engine.components.physics.XPhysicsComponent;
import game_engine.components.physics.YPhysicsComponent;

public class MovementSystem extends GameSystem {
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	private static final Class<? extends Component> HORIZONTAL_PHYSICS = XPhysicsComponent.class;
	private static final Class<? extends Component> VERTICAL_PHYSICS = YPhysicsComponent.class;

	public MovementSystem(Engine engine) {
		super(engine);
	}

	// https://gamedev.stackexchange.com/questions/29617/how-to-make-a-character-jump
	// http://jsfiddle.net/LyM87/3267/
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(VERTICAL_PHYSICS, HORIZONTAL_PHYSICS, POSITION);
		for (Entity e : getEngine().getEntitiesContaining(args)) {
			XPhysicsComponent xPhysics = (XPhysicsComponent) e.getComponent(HORIZONTAL_PHYSICS);
			YPhysicsComponent yPhysics = (YPhysicsComponent) e.getComponent(VERTICAL_PHYSICS);
			PositionComponent position = (PositionComponent) e.getComponent(POSITION);
			
			position.setX(position.getX() + xPhysics.getCurrVel() * elapsedTime);
			position.setY(position.getY() + yPhysics.getCurrVel() * elapsedTime);
		
			yPhysics.setCurrVel(yPhysics.getCurrVel() + -1 * yPhysics.getAccel() * elapsedTime);
			xPhysics.setCurrVel(xPhysics.getCurrVel() + xPhysics.getAccel() * elapsedTime);
		}
	}
}
