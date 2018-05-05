package game_engine.components.projectile;

import game_engine.components.collision.hitbox.HitboxYOffsetComponent;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the an Entity's projectile's hitbox y offset
 * @author Kevin Deng
 *
 */
@DataConditionable
public class ProjectileHitboxYOffsetComponent extends HitboxYOffsetComponent{

	public ProjectileHitboxYOffsetComponent(String arg) {
		super(arg);
	}

}
