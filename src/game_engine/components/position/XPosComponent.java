package game_engine.components.position;

import game_engine.Component;

public class XPosComponent extends Component<Double> {
	
	public XPosComponent(String arg) {
		super(Double.parseDouble(arg));
	}
	
}
