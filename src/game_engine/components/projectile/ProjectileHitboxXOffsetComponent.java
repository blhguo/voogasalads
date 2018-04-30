package game_engine.components.projectile;

import game_engine.components.collision.hitbox.HitboxXOffsetComponent;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class ProjectileHitboxXOffsetComponent extends HitboxXOffsetComponent{

	public ProjectileHitboxXOffsetComponent(String arg) {
		super(arg);
	}

}
