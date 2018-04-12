package game_engine.systems.collision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.PositionComponent;
import game_engine.components.collision.CollidableComponent;
import game_engine.components.collision.CollidedComponent;
//import game_engine.components.collision.CollidedComponent;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.components.collision.hitbox.HitboxComponent;
import game_engine.components.physics.XPhysicsComponent;
import game_engine.components.physics.YPhysicsComponent;

/**
 * @author: Jeremy Chen
 * Broad-phase collision checking. Uses AABB (axiss-aligned bounding boxes) to filter list of all entities down to pairs of entities that may be colliding
 */
public class CollisionBroadSystem extends CollisionSystem {
    private static final Class<? extends Component> XPHYSICS = XPhysicsComponent.class;
    private static final Class<? extends Component> YPHYSICS = YPhysicsComponent.class;
    private static final Class<? extends Component> POSITION = PositionComponent.class;
    private static final Class<? extends Component> COLLIDABLE = CollidableComponent.class;
    private static final Class<? extends Component> HITBOX = HitboxComponent.class;
    
    private static final List<Class<? extends Component>> TARGET_COMPONENTS = Collections.unmodifiableList(
    		new ArrayList<Class<? extends Component>>() {{ 
    			add(XPHYSICS);
    			add(YPHYSICS);
    			add(POSITION);
    			add(COLLIDABLE);
    			add(HITBOX);
    		}});

    /**
     *
     * @param engine
     * Constructor
     */
    public CollisionBroadSystem(Engine engine) {
        super(engine);
    }


    /**
     * @param elapsedTime
     * 
     * Main loop for CollisionBraodSystem: Will perform garbage collection of collidedComponents from last cycle
     * And will loop over every Collidable Entity to check for collisions
     */
    @Override
    public void act(double elapsedTime){
        List<Entity> collideableEntities = getEngine().getEntitiesContaining(TARGET_COMPONENTS);
        collideableEntities.forEach( (entity) -> entity.removeComponent(TopCollidedComponent.class));
        collideableEntities.forEach( (entity) -> entity.removeComponent(LeftCollidedComponent.class));
        collideableEntities.forEach( (entity) -> entity.removeComponent(BottomCollidedComponent.class));
        collideableEntities.forEach( (entity) -> entity.removeComponent(RightCollidedComponent.class));
        
        for(int i = 0; i < collideableEntities.size(); i ++) {
            for(int j = 0; j<collideableEntities.size(); j ++) {
            	if(i!=j) {
	                Entity e1 = collideableEntities.get(i);
	                Entity e2 = collideableEntities.get(j);
	                checkIntersect(e1, e2, elapsedTime);
            	}
            }
        }

    }

    /**
     *
     * @param e1
     * @param e2
     * @return
     * Method that will check for overlap between two AABB (Axis-Aligned Bounding Box) and also determine which side each Entity has been collided
     * TODO: NEEDS SIGNIFICANT REFACTORING
     */
    @Override
    protected void checkIntersect(Entity e1, Entity e2, double elapsedTime){
        double[] aabb1 = getExtrema(e1, elapsedTime);
        double[] aabb2 = getExtrema(e2, elapsedTime);

        boolean xOverlap = Math.max(aabb1[0], aabb2[0]) <= Math.min(aabb1[1], aabb2[1]);
        boolean yOverlap = Math.max(aabb1[2], aabb2[2]) <= Math.min(aabb1[3], aabb2[3]);

        if(xOverlap && yOverlap){
            double xMin1 = aabb1[0];
            double xMin2 = aabb2[0];

            double xMax1 = aabb1[1];
            double xMax2 = aabb2[1];

            double yMin1 = aabb1[2];
            double yMin2 = aabb2[2];

            double yMax1 = aabb1[3];
            double yMax2 = aabb2[3];

            boolean right = xMin2 <= xMax1 && xMax2 >= xMax1;
            boolean left = xMin2 <= xMin1 && xMax2 >= xMin1;
            boolean rlSmall = yMax2 <= yMax1 && yMin2 >= yMin1;
            boolean rlBig = yMax2 >= yMax1 && yMin2 <= yMin1;

            boolean top = yMax2 >= yMin1 && yMin2 <= yMin1;
            boolean bottom = yMax2 >= yMax1 && yMin2 <= yMax1;
            boolean tbBig = xMin2 <= xMin1 && xMax2 >= xMax1;
<<<<<<< HEAD
            boolean tbSmall = xMin2 >= xMin1 && xMax2 <= xMax1;
            
=======
            boolean tbSmall = xMin2 >= xMin1 && xMax2 <= xMax1;            

>>>>>>> liam
            ArrayList<CollidedComponent> collidedToAdd = new ArrayList<CollidedComponent>();
            
            CollidedComponent r = new RightCollidedComponent();
            CollidedComponent l = new LeftCollidedComponent();
            CollidedComponent t = new TopCollidedComponent();
            CollidedComponent b = new BottomCollidedComponent();
            if(right && (rlSmall || rlBig)) {
//                System.out.println("RIGHT");
                collidedToAdd.add(r);
            }
            else if(left && (rlSmall || rlBig)) {
//                System.out.println("LEFT");
                collidedToAdd.add(l);
            }
            else if(bottom && (tbSmall || tbBig)){
//                System.out.println("BOTTOM");
                collidedToAdd.add(b);
            }
            else if(top && (tbSmall || tbBig)){
//                System.out.println("TOP");
                collidedToAdd.add(t);
            }
            else if(bottom && left) {
                double dx = Math.abs(xMax2 - xMin1);
                double dy = Math.abs(yMin2 - yMax1);
                System.out.println("BL dx: " + dx + " dy: " + dy);
                if(dx>dy){
                    System.out.println("BL BOTTOM");
                    collidedToAdd.add(b);
                }
                else if(dy>dx){
                    System.out.println("BL LEFT");
                    collidedToAdd.add(l);
                }
                else{
                    System.out.println("BL BOTTOM LEFT");
                	collidedToAdd.add(b);
                    collidedToAdd.add(l);
                }
            }
            else if(bottom & right){
            	System.out.println("BOTTOM RIGHT");
                double dx = Math.abs(xMax1 - xMin2);
                double dy = Math.abs(yMax1 - yMin2);
                if(dx>dy){
//                    System.out.println("BOTTOM");
                	collidedToAdd.add(b);
                }
                else if(dx<dy){
//                    System.out.println("RIGHT");
                	collidedToAdd.add(r);
                }
                else{
//                    System.out.println("BOTTOM RIGHT");
                	collidedToAdd.add(b);
                	collidedToAdd.add(r);
                }
            }
            else if(top && left){
            	System.out.println("TOP LEFT");
                double dx = Math.abs(xMin1 - xMax2);
                double dy = Math.abs(yMin1 - yMax2);
                if(dx>dy){
//                    System.out.println("TOP");
                	collidedToAdd.add(t);
                }
                else if(dy>dx){
//                    System.out.println("LEFT");
                	collidedToAdd.add(l);
                }
                else{
//                    System.out.println("TOP LEFT");
                	collidedToAdd.add(t);
                	 collidedToAdd.add(t);
                }
            }
            else if(top && right) {
                System.out.println("TOP RIGHT");
                double dx = Math.abs(xMin2 - xMax1);
                double dy = Math.abs(yMin1 - yMax2);
                if(dx>dy){
                    System.out.println("TOP");
                	collidedToAdd.add(t);
                }
                else if(dy>dx){
                    System.out.println("right");
                	collidedToAdd.add(r);
                }
                else{
                	System.out.println("top right");
                	collidedToAdd.add(t);
                	collidedToAdd.add(r);
                }
            }
            
            for(CollidedComponent c: collidedToAdd) {
            	c.addEntity(e2);
            	e1.addComponent(c);
            }
        }
    }
}
