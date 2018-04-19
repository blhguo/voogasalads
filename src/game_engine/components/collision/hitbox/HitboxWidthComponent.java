package game_engine.components.collision.hitbox;

import game_engine.Component;

public class HitboxWidthComponent extends Component<Double>{

	public HitboxWidthComponent(String arg) {
		super(Double.parseDouble(arg));
	}	
}
