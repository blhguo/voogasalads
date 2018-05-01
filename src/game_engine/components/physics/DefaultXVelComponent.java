package game_engine.components.physics;

import game_engine.Component;

public class DefaultXVelComponent extends Component<Double> {

	public DefaultXVelComponent(String arg) {
		super(Double.parseDouble(arg));
	}

}
