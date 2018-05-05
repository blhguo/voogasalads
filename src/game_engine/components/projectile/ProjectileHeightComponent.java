package game_engine.components.projectile;

import game_engine.components.sprite.HeightComponent;
import game_engine.event.conditions.DataConditionable;

/**
 * Specifies the an Entity's projectile's height
 * @author Kevin Deng
 *
 */
@DataConditionable
public class ProjectileHeightComponent extends HeightComponent {

	public ProjectileHeightComponent(String arg) {
		super(arg);
	}

}
