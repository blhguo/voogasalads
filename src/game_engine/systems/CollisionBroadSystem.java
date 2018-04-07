package game_engine.systems;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.CollidableComponent;
import game_engine.components.HitboxComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;

import java.util.List;

/**
 * @author: Jeremy Chen
 * Broad-phase collision checking. Uses AABB (axiss-aligned bounding boxes) to filter list of all entities down to pairs of entities that may be colliding
 */
public class CollisionBroadSystem extends CollisionSystem {
    private static final Class<? extends Component> PHYSICS = PhysicsComponent.class;
    private static final Class<? extends Component> POSITION = PositionComponent.class;
    private static final Class<? extends Component> COLLIDABLE = CollidableComponent.class;
    private static final Class<? extends Component> HITBOX = HitboxComponent.class;

    /**
     *
     * @param engine
     */
    public CollisionBroadSystem(Engine engine) {
        super(engine);
    }

    @Override
    public void act(double elapsedTime){
        List<Entity> collideableEntities = getEngine().getEntitiesContaining(PHYSICS, POSITION, COLLIDABLE);
    }

    /**
     *
     * @param e1
     * @param e2
     * @return
     */
    @Override
    protected boolean intersect(Entity e1, Entity e2){
        double[] aabb1 = getExtrema(e1);
        double[] aabb2 = getExtrema(e2);

        // need to add dx dy compensation

        boolean xOverlap = Math.max(aabb1[0], aabb2[0]) <= Math.min(aabb1[1], aabb2[1]);
        boolean yOverlap = Math.max(aabb1[2], aabb2[2]) <= Math.min(aabb1[3], aabb2[3]);

        return xOverlap && yOverlap;
    }
}
