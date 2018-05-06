package game_engine.components.projectile;

import game_engine.components.physics.YVelComponent;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the an Entity's projectile's default y velocity
 * @author Kevin Deng
 *
 */
@DataConditionable
public class ProjectileYVelComponent extends YVelComponent{

	public ProjectileYVelComponent(String arg) {
		super(arg);
	}

}
