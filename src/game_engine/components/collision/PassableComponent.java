package game_engine.components.collision;

import game_engine.Component;

public class PassableComponent extends Component<Boolean> {
	public PassableComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}
}
