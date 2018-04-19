package game_engine.systems.collision;

import game_engine.Engine;
import game_engine.Entity;

/**
 * @author Jeremy Chen
 * Yet to be implemented. Meant to describe bounds of a circular hitbox when Narrow-phase collisions are implemented
 *
 */
public class CollisionCircleSystem extends CollisionNarrowSystem {


    public CollisionCircleSystem(Engine engine) {
        super(engine);
    }

//    @Override
//    protected void intersect(Entity e1, Entity e2){
//
//    }

	@Override
	public void act(double elapsedTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void checkIntersect(Entity e1, Entity e2, double elapsedTime) {
		// TODO Auto-generated method stub
		
	}
}
