package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.CollidableComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;

public class CollisionSystem extends GameSystem{
	private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;
	private static final Class<? extends Component> POSITION = PositionComponent.class;
	private static final Class<? extends Component> COLLIDABLE = CollidableComponent.class;
	
	public CollisionSystem(Engine engine) {
		super(engine);
	}

	@Override
	public void act(double elapsedTime) {
		List<Class<? extends Component>> args = Arrays.asList(PHYSICS, POSITION, COLLIDABLE);
		List<Entity> entities = getEngine().getEntitiesContaining(args);
		for (Entity e : entities) {
			for(Entity collidedWith: entities){
				if(collidedWith == e) continue;
				PhysicsComponent physics = (PhysicsComponent) e.getComponent(PHYSICS);
				PositionComponent position = (PositionComponent) e.getComponent(POSITION);
				CollidableComponent collidable = (CollidableComponent) e.getComponent(COLLIDABLE);
				//TODO: check if collided and then perform action based off of collision
			}
		}
	}
	
}
