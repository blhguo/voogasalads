package game_engine.components.projectile;

import game_engine.components.physics.XVelComponent;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the an Entity's projectile's default x velocity
 * @author Kevin Deng
 *
 */
@DataConditionable
public class ProjectileXVelComponent extends XVelComponent{

	public ProjectileXVelComponent(String arg) {
		super(arg);
	}

}
