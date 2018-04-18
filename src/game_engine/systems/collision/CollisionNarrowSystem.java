package game_engine.systems.collision;

import game_engine.Engine;
import game_engine.Entity;

/*
 * @author: Jeremy Chen
 * Abstract class for narrow-phase collision detection. Abstract to allow for new/different hitbox shapes in the future
 */
public abstract class CollisionNarrowSystem extends CollisionSystem {

    public CollisionNarrowSystem(Engine engine) {
        super(engine);
    }

}
