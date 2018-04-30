package game_engine.components;

import game_engine.Component;

public class TimerComponent extends Component<Double>{

	public TimerComponent(String val) {
		super(Double.parseDouble(val));
	}

}
