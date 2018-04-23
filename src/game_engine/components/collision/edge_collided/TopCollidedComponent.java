package game_engine.components.collision.edge_collided;

import game_engine.components.collision.CollidedComponent;

/**
 * @author Jeremy Chen
 * Temporary subclass of CollidedComponent, that allows systems to distinguish which side (in this case top) of an AABB an entity has been collided with
 */
public class TopCollidedComponent extends CollidedComponent {}
