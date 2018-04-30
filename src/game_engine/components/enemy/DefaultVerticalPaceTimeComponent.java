package game_engine.components.enemy;

import game_engine.Component;

public class DefaultVerticalPaceTimeComponent extends Component<Double> {

	public DefaultVerticalPaceTimeComponent(String val) {
		super(Double.parseDouble(val));
	}

}
