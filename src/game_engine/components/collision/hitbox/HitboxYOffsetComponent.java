package game_engine.components.collision.hitbox;

import game_engine.Component;

public class HitboxYOffsetComponent extends Component<Double>{

	public HitboxYOffsetComponent(String arg) {
		super(Double.parseDouble(arg));
	}	
}
