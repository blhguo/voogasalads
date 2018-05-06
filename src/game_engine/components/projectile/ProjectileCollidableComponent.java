package game_engine.components.projectile;

import game_engine.components.collision.CollidableComponent;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the an Entity's projectile's collidable component
 * @author Kevin Deng
 *
 */
@DataConditionable
public class ProjectileCollidableComponent extends CollidableComponent {

	public ProjectileCollidableComponent(String arg) {
		super(arg);
	}

}
