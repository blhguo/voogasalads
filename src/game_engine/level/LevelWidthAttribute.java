package game_engine.level;

import game_engine.Component;

public class LevelWidthAttribute extends Component<Double>{

	public LevelWidthAttribute(String arg) {
		super(Double.parseDouble(arg));
	}

}
