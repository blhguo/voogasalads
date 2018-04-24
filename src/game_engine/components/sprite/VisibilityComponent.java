package game_engine.components.sprite;

import game_engine.Component;

public class VisibilityComponent extends Component<Boolean> {

	public VisibilityComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}
	
}
