package game_engine.systems.collision;

import java.util.ArrayList;
import java.util.Collections;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.collision.hitbox.HitboxHeightComponent;
import game_engine.components.collision.hitbox.HitboxWidthComponent;
import game_engine.components.collision.hitbox.HitboxXOffsetComponent;
import game_engine.components.collision.hitbox.HitboxYOffsetComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.components.position.AngleComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;

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
		double centerX = e.getComponent(XPosComponent.class).getValue();
		double centerY = e.getComponent(YPosComponent.class).getValue();
		
		double xOffset = e.getComponent(HitboxXOffsetComponent.class).getValue();
		double yOffset = e.getComponent(HitboxYOffsetComponent.class).getValue();
		double width = e.getComponent(HitboxWidthComponent.class).getValue();
		double height = e.getComponent(HitboxHeightComponent.class).getValue();
		
		Double currXVel = e.getComponent(XVelComponent.class).getValue();
		Double currYVel = e.getComponent(YVelComponent.class).getValue();
		Double currAngle = e.getComponent(AngleComponent.class).getValue();
		
		if(currXVel!=null) {
			centerX += currXVel*elapsedTime;
		}
		if(currYVel!=null) {
			centerY += currYVel*elapsedTime;
		}
		if(currAngle%90 == 0){
			return new double[]{centerX - width/2, centerX + width/2, centerY - height/2, centerY + height/2};
		}
		
		// TODO: fixed rotation/transformed coordinates
		ArrayList<Double> xCoords = new ArrayList<Double>();
		ArrayList<Double> yCoords = new ArrayList<Double>();
		for(int i = -1; i <=1; i+=2){
			for(int j = -1; j<=1; j+=2){
				double origX = i*width + centerX;
				double origY = j*height + centerY;

				double transformedX = centerX+(origX-centerX)*Math.cos(currAngle)+(origY-centerY)*Math.sin(currAngle);
				double transformedY = centerY-(origX-centerX)*Math.sin(currAngle)+(origY-centerY)*Math.cos(currAngle);
				xCoords.add(transformedX);
				yCoords.add(transformedY);
			}
		}
		return new double[]{Collections.min(xCoords), Collections.max(xCoords), Collections.min(yCoords), Collections.max(yCoords)};
	}

//    /**
//     * @param e1
//     * @param e2
//     * @param colSide
//     * Method that allows for injection of the CollidedComponetn
//     * NEEDS REFACTORING
//     */
//    protected void addCollided(Entity e1, Entity e2, Class colSide){
//	    CollidedComponent colComp = (CollidedComponent) e1.getComponent(colSide);
//        if(colComp == null) {
//            try {
//                Constructor<?> colCon = colSide.getConstructor();
//                colComp = (CollidedComponent) colCon.newInstance();
//                e1.addComponent(colComp);
//            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
//                System.out.println("temp err msg");
//            }
//        }
//        colComp.addEntity(e2);
//    }
}
