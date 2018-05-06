package game_engine.components;

import game_engine.Component;
import game_engine.NullType;

/**
 * The DespawnComponent specifies if an Entity can despawn from the screen (be invisible)
 * It is instantiated within the Authoring Enviornment
* @author Kevin Deng, Andy Nguyen, Ben Hubsch, Jeremy Chen *
 */

public class DespawnComponent extends Component<NullType>{

	public DespawnComponent() {
		super(null);
	}

	public DespawnComponent(String val) {
		this();
	}

}
