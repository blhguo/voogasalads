package game_engine.components;

import game_engine.Component;
import game_engine.NullType;

/**
 * The Projectile Component specifies if an Entity can launch a projectile (shoot an object)
* @author Kevin Deng, Andy Nguyen, Ben Hubsch, Jeremy Chen *
 */
public class ProjectileComponent extends Component<NullType>{

	public ProjectileComponent() {
		super(null);
	}
	public ProjectileComponent(String val) {
		super(null);
	}

}
