package game_engine.components.projectile;

import game_engine.components.sprite.WidthComponent;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class ProjectileWidthComponent extends WidthComponent{

	public ProjectileWidthComponent(String arg) {
		super(arg);
	}

}
