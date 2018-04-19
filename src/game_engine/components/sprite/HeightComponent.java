package game_engine.components.sprite;

import game_engine.Component;

public class HeightComponent extends Component<Boolean> {
	
	public HeightComponent(String arg) {
		super(Boolean.parseBoolean(arg));
	}

}
