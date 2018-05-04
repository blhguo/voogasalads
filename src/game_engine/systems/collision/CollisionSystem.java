package game_engine.systems.collision;

import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.collision.hitbox.HitboxHeightComponent;
import game_engine.components.collision.hitbox.HitboxWidthComponent;
import game_engine.components.collision.hitbox.HitboxXOffsetComponent;
import game_engine.components.collision.hitbox.HitboxYOffsetComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import javafx.geometry.Point2D;

/**
 * @author Jeremy Chen Superclass for all GameSystems that contain logic for collision detection. Contains
 *         shared method signature / several sharedutility methods
 */
public abstract class CollisionSystem implements GameSystem {

	/**
	 *
	 * @param e1
	 * @param e2
	 * @return
	 * 
	 * 		Method that will check for intersections between two passed entities & injects
	 *         CollidedComponent accordingly
	 * 
	 */
	protected abstract void checkIntersect(Entity e1, Entity e2, double elapsedTime);

	/**
	 * 
	 * @param e
	 * @return
	 * 
	 * 		Helper method that gets extrema of a sprite (min/max x & y coordinates), used for
	 *         creating an AABB, among other applications, will return in the form [min_x, max_x, min_y,
	 *         max_y]
	 * 
	 *
	 */
	protected double[] getExtrema(Entity e, double elapsedTime) {
		double xOffset = e.getComponent(HitboxXOffsetComponent.class).getValue();
		double yOffset = e.getComponent(HitboxYOffsetComponent.class).getValue();

		double centerX = e.getComponent(XPosComponent.class).getValue() + xOffset;
		double centerY = e.getComponent(YPosComponent.class).getValue() + yOffset;

		double width = e.getComponent(HitboxWidthComponent.class).getValue();
		double height = e.getComponent(HitboxHeightComponent.class).getValue();

		XVelComponent xVel = (XVelComponent) e.getComponent(XVelComponent.class);
		YVelComponent yVel = (YVelComponent) e.getComponent(YVelComponent.class);

		if (xVel != null) {
			centerX += xVel.getValue() * elapsedTime;
		}
		if (yVel != null) {
			centerY += yVel.getValue() * elapsedTime;
		}
		return new double[] { centerX - width / 2, centerX + width / 2, centerY - height / 2,
				centerY + height / 2 };
	}

	/**
	 * @param point
	 * @param angle
	 * @return
	 * 
	 * 		Will return a transformed point (assumed to be rotated about origin), rotated by a
	 *         specified degree angle (Using javaFX coordinate/angle system, with North being 0 degrees,
	 *         and positive rotation being clockwise
	 */
	public Point2D transformPoint(Point2D point, double angle) {
		double radAngle = Math.toRadians(angle);
		double transX = point.getX() * Math.cos(radAngle) - point.getY() * Math.sin(radAngle);
		double transY = point.getY() * Math.cos(radAngle) + point.getX() * Math.sin(radAngle);
		return new Point2D(transX, transY);
	}

	/**
	 *
	 * @param point
	 * @param origin
	 * @param angle
	 * @return
	 *
	 * Alternative signature for transforming point by angle
	 */
	public Point2D transformPoint(Point2D point, Point2D origin, double angle) {
		Point2D relativeCoord = new Point2D(point.getX() - origin.getX(), point.getY() - origin.getY());
		Point2D transRelative = transformPoint(relativeCoord, angle);

		return new Point2D(transRelative.getX() + origin.getX(), transRelative.getY() + origin.getY());
	}
}
