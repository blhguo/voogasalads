package game_engine.components.projectile;

import game_engine.components.collision.hitbox.HitboxYOffsetComponent;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class ProjectileHitboxYOffsetComponent extends HitboxYOffsetComponent{

	public ProjectileHitboxYOffsetComponent(String arg) {
		super(arg);
	}

}
