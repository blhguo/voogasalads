package game_engine.components;

import game_engine.Component;

public class OnGroundComponent extends Component<Boolean> {
	public OnGroundComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}
}
