package game_engine.systems;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.CollidableComponent;
import game_engine.components.CollidedComponent;
import game_engine.components.HitboxComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
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
    
    private static final List<Class<? extends Component>> TARGET_COMPONENTS = Collections.unmodifiableList(
    		new ArrayList<Class<? extends Component>>() {{ 
    			add(PHYSICS);
    			add(POSITION);
    			add(COLLIDABLE);
    		}});

    /**
     *
     * @param engine
     */
    public CollisionBroadSystem(Engine engine) {
        super(engine);
    }

    @Override
    public void act(double elapsedTime){
        List<Entity> collideableEntities = getEngine().getEntitiesContaining(TARGET_COMPONENTS);
        // CLEANUP
        collideableEntities.forEach( (entity) -> entity.removeComponent(CollidedComponent.class));
//        List<Pair> possibleCollisions = new ArrayList<Pair>();
        for(int i = 0; i < collideableEntities.size()-1; i ++) {
        	for(int j = i + 1; j<collideableEntities.size(); j ++) {
        		Entity e1 = collideableEntities.get(i);
        		Entity e2 = collideableEntities.get(j);
        		if(intersect(e1, e2)) {
//        			possibleCollisions.add(new Pair<Entity, Entity>(e1, e2));
        			e1.addComponent(new CollidedComponent());
        			e2.addComponent(new CollidedComponent());
        		}
        	}
        }
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
