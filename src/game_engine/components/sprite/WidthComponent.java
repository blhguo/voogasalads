package game_engine.components.sprite;

import game_engine.Component;

public class WidthComponent extends Component<Double> {
	
	public WidthComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
