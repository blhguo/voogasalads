package game_engine.components;

import game_engine.Component;
import game_engine.event.conditions.DataConditionable;

@DataConditionable
public class LivesComponent extends Component<Double>{
	public LivesComponent(String arg) {
		super(Double.parseDouble(arg));
	}
}
