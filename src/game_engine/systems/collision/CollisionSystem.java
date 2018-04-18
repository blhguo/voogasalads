package game_engine.systems.collision;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;

/**
 * @author Jeremy Chen
 * Superclass for all GameSystems that contain logic for collisions
 * Contains shared method signature / several sharedutility methods
 */
public abstract class CollisionSystem extends GameSystem {

	/**
	 * @param engine
	 * Constructor, needs reference to engine to access entities
	 */
	public CollisionSystem(Engine engine) {
		super(engine);
	}

	/**
	 *
	 * @param e1
	 * @param e2
	 * @return
	 * 
	 * Method that will check for intersections between two passed entities & injects CollidedComponent accordingly
	 * 
	 */
	protected abstract void checkIntersect(Entity e1, Entity e2, double elapsedTime);
	
	/**
	 *  
	 * @param e
	 * @return
	 * 
	 * Helper method that gets extrema of a sprite (min/max x & y coordinates), used for creating
	 *  an AABB, among other applications, will return in the form [min_x, max_x, min_y, max_y]
	 */
	protected double[] getExtrema(Entity e, double elapsedTime){
		PositionComponent p = (PositionComponent) e.getComponent(PositionComponent.class);
		HitboxComponent h = (HitboxComponent) e.getComponent(HitboxComponent.class);

		double angle = Math.toRadians(p.getAngle());
		double width = h.getWidth();
		double height = h.getHeight();
		double centerX = p.getX() + h.getXOffset();
		double centerY = p.getY() + h.getYOffset();
		
		XPhysicsComponent xp = (XPhysicsComponent) e.getComponent(XPhysicsComponent.class);
		YPhysicsComponent yp = (YPhysicsComponent) e.getComponent(YPhysicsComponent.class);
		
		if(xp!=null) {
			centerX += xp.getCurrVel()*elapsedTime;
		}
		if(yp!=null) {
			centerY += yp.getCurrVel()*elapsedTime;
		}
		if(p.getAngle()%90 == 0){
			return new double[]{centerX - width/2, centerX + width/2, centerY - height/2, centerY + height/2};
		}
		
		// TODO: fixed rotation/transformed coordinates
		ArrayList<Double> xCoords = new ArrayList<Double>();
		ArrayList<Double> yCoords = new ArrayList<Double>();
		for(int i = -1; i <=1; i+=2){
			for(int j = -1; j<=1; j+=2){
				double origX = i*width + centerX;
				double origY = j*height + centerY;

				double transformedX = centerX+(origX-centerX)*Math.cos(angle)+(origY-centerY)*Math.sin(angle);
				double transformedY = centerY-(origX-centerX)*Math.sin(angle)+(origY-centerY)*Math.cos(angle);
				xCoords.add(transformedX);
				yCoords.add(transformedY);
			}
		}
		return new double[]{Collections.min(xCoords), Collections.max(xCoords), Collections.min(yCoords), Collections.max(yCoords)};
	}

    /**
     * @param e1
     * @param e2
     * @param colSide
     * Method that allows for injection of the CollidedComponetn
     * NEEDS REFACTORING
     */
    protected void addCollided(Entity e1, Entity e2, Class colSide){
	    CollidedComponent colComp = (CollidedComponent) e1.getComponent(colSide);
        if(colComp == null) {
            try {
                Constructor<?> colCon = colSide.getConstructor();
                colComp = (CollidedComponent) colCon.newInstance();
                e1.addComponent(colComp);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                System.out.println("temp err msg");
            }
        }
        colComp.addEntity(e2);
    }
}
