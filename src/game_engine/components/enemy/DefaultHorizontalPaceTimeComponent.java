package game_engine.components.enemy;

import game_engine.Component;

public class DefaultHorizontalPaceTimeComponent extends Component<Double> {

	public DefaultHorizontalPaceTimeComponent(String val) {
		super(Double.parseDouble(val));
	}

}
