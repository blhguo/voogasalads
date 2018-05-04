package game_engine.systems.collision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.level.Level;

/**
 * @author Jeremy Chen
 * Abstract superclass that describes common behavior for any system that acts on entities that have been collided with
 */
public abstract class CollisionResponseSystem implements GameSystem {
    protected static final Class<? extends Component<List<Entity>>> TOP = TopCollidedComponent.class;
    protected static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
    protected static final Class<? extends Component<List<Entity>>> RIGHT = RightCollidedComponent.class;
    protected static final Class<? extends Component<List<Entity>>> LEFT = LeftCollidedComponent.class;
    private static final List<Class<? extends Component<?>>> COLLIDED_ARGS = Arrays.asList(TOP, BOTTOM, RIGHT, LEFT);

    /**
     * Simple helper that gets all entities that have been collided with (containing any type of CollidedComponent)
     * @param level
     * @return
     */
    protected List<Entity> getCollidedEntities(Level level) {
        return level.getEntitiesContainingAny(COLLIDED_ARGS);
    }

    /**
     * Will return subset of entities that have been collided from list of entities given
     * @param entities
     * @param level
     * @return
     */
    protected List<Entity> getCollidedEntities(List<Entity> entities, Level level) {
        return level.getEntitiesContainingAny(entities, COLLIDED_ARGS);
    }

    /**
     * Will return a list of entities that the provided entity has collided with
     * @param e
     * @param <T>
     * @return
     */
    protected <T> List<Entity> getAllCollidedWith(Entity e) {
        List<Entity> entitiesCollidedWith = new ArrayList<>();

        COLLIDED_ARGS.stream().forEach(cc -> {
            Component<T> collidedComponent = e.getComponent((Class<? extends Component<T>>) cc);
            if (collidedComponent != null) {
                List<Entity> others = (List<Entity>) collidedComponent.getValue();
                if (others != null) {
                    entitiesCollidedWith.addAll(others);
                }
            }
        });
        return entitiesCollidedWith;
    }
}
