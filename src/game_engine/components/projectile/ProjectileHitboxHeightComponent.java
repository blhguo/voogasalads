package game_engine.components.projectile;

import game_engine.components.collision.hitbox.HitboxHeightComponent;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the an Entity's projectile's hitbox height
 * @author Kevin Deng
 *
 */
@DataConditionable
public class ProjectileHitboxHeightComponent extends HitboxHeightComponent{

	public ProjectileHitboxHeightComponent(String arg) {
		super(arg);
	}

}
