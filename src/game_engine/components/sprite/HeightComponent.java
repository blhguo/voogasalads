package game_engine.components.sprite;

import game_engine.Component;

public class HeightComponent extends Component<Double> {
	
	public HeightComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
