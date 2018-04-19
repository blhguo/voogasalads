package game_engine.components.collision;

import game_engine.Component;

public class PushableComponent extends Component<Boolean> {
	public PushableComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}
}
