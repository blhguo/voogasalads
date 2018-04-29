package game_engine.components.projectile;

import game_engine.components.collision.hitbox.HitboxHeightComponent;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class ProjectileHitboxHeightComponent extends HitboxHeightComponent{

	public ProjectileHitboxHeightComponent(String arg) {
		super(arg);
	}

}
