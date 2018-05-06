package game_engine.components.projectile;

import game_engine.components.collision.hitbox.HitboxWidthComponent;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the an Entity's projectile's hitbox width
 * @author Kevin Deng
 *
 */
@DataConditionable
public class ProjectileHitboxWidthComponent extends HitboxWidthComponent{

	public ProjectileHitboxWidthComponent(String arg) {
		super(arg);
	}

}
