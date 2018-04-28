package game_engine.components.projectile;

import game_engine.components.collision.hitbox.HitboxWidthComponent;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class ProjectileHitboxWidthComponent extends HitboxWidthComponent{

	public ProjectileHitboxWidthComponent(String arg) {
		super(arg);
	}

}
