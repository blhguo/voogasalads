package game_engine.systems;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Jeremy Chen
 */

public class CollisionResponseSystem extends GameSystem{

    private static final Class<? extends Component> LEFT = LeftCollidedComponent.class;
    private static final Class<? extends Component> BOTTOM = BottomCollidedComponent.class;
    private static final Class<? extends Component> RIGHT = RightCollidedComponent.class;
    private static final Class<? extends Component> TOP = TopCollidedComponent.class;

    private static final List<Class<? extends Component>> TARGET_COMPONENTS = Collections.unmodifiableList(
            new ArrayList<Class<? extends Component>>() {{
                add(LEFT);
                add(RIGHT);
                add(BOTTOM);
                add(TOP);
            }});

    public CollisionResponseSystem(Engine engine) {
        super(engine);
    }

    @Override
    public void act(double elapsedTime) {
        List<Entity> collidedEntities = getEngine().getEntitiesContainingAny(TARGET_COMPONENTS);
        for (Entity e: collidedEntities){
            PhysicsComponent p = (PhysicsComponent) e.getComponent(PhysicsComponent.class);
            if((e.getComponent(LEFT) != null && p.getCurrXVel()<0) ||
                    (e.getComponent(RIGHT) != null && p.getCurrXVel() > 0)){
                p.setCurrXVel(0.0);
            }
            if(e.getComponent(BOTTOM) != null && p.getCurrYVel()<0 ||
                    (e.getComponent(TOP) != null && p.getCurrYVel() > 0)){
                p.setCurrYVel(0.0);
            }
        }
    }
}
