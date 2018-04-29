package game_engine.components.projectile;

import game_engine.components.DamageComponent;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class ProjectileDamageComponent extends DamageComponent{

	public ProjectileDamageComponent(String arg) {
		super(arg);
	}

}
