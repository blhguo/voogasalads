package game_engine.systems.collision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.components.physics.XPhysicsComponent;
import game_engine.components.physics.YPhysicsComponent;

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
            XPhysicsComponent xp = (XPhysicsComponent) e.getComponent(XPhysicsComponent.class);
            YPhysicsComponent yp = (YPhysicsComponent) e.getComponent(YPhysicsComponent.class);
                        
            if(xp!=null && ((e.getComponent(LEFT) != null && xp.getCurrVel() < 0) ||
                    (e.getComponent(RIGHT) != null && xp.getCurrVel() > 0))){
            	System.out.println(" ||| I AINT FINNA BE UR SIDE HOE ||| ");
                xp.setCurrVel(0.0);
            }
            if(yp!=null && ((e.getComponent(BOTTOM) != null && yp.getCurrVel() > 0) ||
                    (e.getComponent(TOP) != null && yp.getCurrVel() < 0))){
                yp.setCurrVel(0.0);
            }
        }
    }
}
