package game_engine.components.enemy;

import game_engine.Component;

public class HorizontalPaceTimeComponent extends Component<Double> {

	public HorizontalPaceTimeComponent(String val) {
		super(Double.parseDouble(val));
	}

}
