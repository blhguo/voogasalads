package game_engine.systems.collision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.collision.CollidableComponent;
import game_engine.components.collision.CollidedComponent;
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
            
        	CollidableComponent c1 = (CollidableComponent) e.getComponent(CollidableComponent.class);
            CollidedComponent l = (CollidedComponent) e.getComponent(LEFT);
            CollidedComponent r = (CollidedComponent) e.getComponent(RIGHT);
            CollidedComponent t = (CollidedComponent) e.getComponent(TOP);
            CollidedComponent b = (CollidedComponent) e.getComponent(BOTTOM);
        	
            if(xp!=null && ((l != null && xp.getCurrVel() < 0) ||
                    (r != null && xp.getCurrVel() > 0))){
                	
            		if(l!=null) {
            			Entity e2 = l.getEntities().get(0);
            			CollidableComponent c2 = (CollidableComponent) e2.getComponent(CollidableComponent.class);
            			XPhysicsComponent xp2 = (XPhysicsComponent) e2.getComponent(XPhysicsComponent.class);
            			if(c2.getPushable() > c1.getPushable()) {
            				xp.setCurrVel(xp2.getCurrVel());
            			}
            			else if(c2.getPushable() < c1.getPushable()) {
            				xp2.setCurrVel(xp.getCurrVel());
            			}
            			else {
            				xp.setCurrVel(0.0);
            			}
            		}
            		else {
            			xp.setCurrVel(0.0);	
            		}
            		
            }
            if(yp!=null && ((e.getComponent(BOTTOM) != null && yp.getCurrVel() > 0) ||
                    (e.getComponent(TOP) != null && yp.getCurrVel() < 0))){
                
            	yp.setCurrVel(0.0);
                
            }
        }
    }
}
