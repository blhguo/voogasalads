package game_engine.components.projectile;

import game_engine.components.physics.YVelComponent;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class ProjectileYVelComponent extends YVelComponent{

	public ProjectileYVelComponent(String arg) {
		super(arg);
	}

}
