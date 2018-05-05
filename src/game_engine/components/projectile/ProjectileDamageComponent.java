package game_engine.components.projectile;

import game_engine.components.DamageComponent;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the an Entity's projectile's Damage component
 * @author Kevin Deng
 *
 */
@DataConditionable
public class ProjectileDamageComponent extends DamageComponent{

	public ProjectileDamageComponent(String arg) {
		super(arg);
	}

}
