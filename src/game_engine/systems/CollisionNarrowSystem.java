package game_engine.systems;

import game_engine.Engine;
import game_engine.Entity;

/*
 * @author: Jeremy Chen
 * Abstract calss for narrow-phase collision detection. Abstract to allow for new/different hitbox shapes in the future
 */
public abstract class CollisionNarrowSystem extends CollisionSystem {

    public CollisionNarrowSystem(Engine engine) {
        super(engine);
    }

    @Override
    protected boolean intersect(Entity e1, Entity e2){
        return false;
    }

}
