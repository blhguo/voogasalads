package game_engine.components.sprite;

import game_engine.Component;

public class WidthComponent extends Component<Integer> {
	
	public WidthComponent(String arg) {
		super(Integer.parseInt(arg));
	}

}
