package game_engine.components.collision.hitbox;

import game_engine.Component;

public class HitboxXOffsetComponent extends Component<Double>{

	public HitboxXOffsetComponent(String arg) {
		super(Double.parseDouble(arg));
	}	
}
