package game_engine.components;

import game_engine.Component;

public class LivesComponent extends Component<Double>{
	public LivesComponent(String arg) {
		super(Double.parseDouble(arg));
	}
}
