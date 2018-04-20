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
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;

/**
 * @author: Jeremy Chen
 * A GameSystem that provides generic behavior for entites that posses a CollidedComponent (have been collided)
 * Describes very basic collision behavior (stopping & pushing)
 * 
 */
public class CollisionResponseSystem extends GameSystem{
    private static final Class<? extends Component<?>> LEFT = LeftCollidedComponent.class;
    private static final Class<? extends Component<?>> BOTTOM = BottomCollidedComponent.class;
    private static final Class<? extends Component<?>> RIGHT = RightCollidedComponent.class;
    private static final Class<? extends Component<?>> TOP = TopCollidedComponent.class;

    private static final List<Class<? extends Component<?>>> TARGET_COMPONENTS = Collections.unmodifiableList(
            new ArrayList<Class<? extends Component<?>>>() {{
                add(LEFT);
                add(RIGHT);
                add(BOTTOM);
                add(TOP);
            }});

    /**
     * @param engine
     * Constructor
     */
    public CollisionResponseSystem(Engine engine) {
        super(engine);
    }

    /* (non-Javadoc)
     * @see game_engine.GameSystem#act(double)
     * Main loop: checks for matching velocity/collision direction, as to stop/push entities in appropraite cases
     */
    @Override
    public void act(double elapsedTime) {
        List<Entity> collidedEntities = getEngine().getEntitiesContainingAny(TARGET_COMPONENTS);
        for (Entity e: collidedEntities){
            XVelComponent xv = (XVelComponent) (e.getComponent(XVelComponent.class));
            YVelComponent yv = (YVelComponent) (e.getComponent(YVelComponent.class));
    		String stopVal = Double.toString(0.0);
    		
//            if(xv!=null && ((e.getComponent(LEFT) != null && xv.getValue() < 0) ||
//                    (e.getComponent(RIGHT) != null && xv.getValue() > 0))){
//                xv.setValue(0.0);
//            }
//            if(yv!=null && ((e.getComponent(BOTTOM) != null && yv.getValue() > 0) ||
//                    (e.getComponent(TOP) != null && yv.getValue() < 0))){
//                yv.setValue(0.0);
//            }
        }
    }
}
