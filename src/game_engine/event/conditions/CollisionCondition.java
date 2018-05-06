package game_engine.event.conditions;

import java.util.Arrays;
import java.util.List;

import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.event.Condition;

/**
 * @author Jeremy Chen
 * Abstract class describing generic behavior for conditions contingent on a collision. Allows for polymorphism in checking
 * certain fields in collided entities to fulfill a condition.
 */
public abstract class CollisionCondition implements Condition {
    List<Class<? extends CollidedComponent>> SIDES_TO_CHECK = Arrays.asList(BottomCollidedComponent.class, LeftCollidedComponent.class, RightCollidedComponent.class, TopCollidedComponent.class);

    Entity myEntity;

    public CollisionCondition(Entity e1) {
        myEntity = e1;
    }

    @Override
    public boolean evaluate() {
        return checkSides();
    }

    private boolean checkSides() {
        for (Class<? extends CollidedComponent> c : SIDES_TO_CHECK) {
            if (c != null) {
                CollidedComponent sideComponent = (CollidedComponent) myEntity.getComponent(c);
                if (sideComponent != null && findCollidedTarget(sideComponent)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected abstract boolean findCollidedTarget(CollidedComponent sideComponent);
}
