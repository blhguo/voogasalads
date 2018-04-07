package game_engine.systems;

import game_engine.Engine;
import game_engine.Entity;

public class CollisionCircleSystem extends CollisionNarrowSystem {


    public CollisionCircleSystem(Engine engine) {
        super(engine);
    }

    @Override
    protected boolean intersect(Entity e1, Entity e2){
        return false;
    }
}
