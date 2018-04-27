package game_engine.components.enemy;

import game_engine.Component;

public class PaceDistanceComponent extends Component<Double> {

	public PaceDistanceComponent(String val) {
		super(Double.parseDouble(val));
	}

}
