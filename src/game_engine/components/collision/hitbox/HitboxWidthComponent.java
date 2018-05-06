package game_engine.components.collision.hitbox;

import game_engine.Component;

/**
 * Defines the Hitbox width of an entity
 * @author Jeremy Chen
 *
 */
public class HitboxWidthComponent extends Component<Double>{

	public HitboxWidthComponent(String arg) {
		super(Double.parseDouble(arg));
	}	
}
