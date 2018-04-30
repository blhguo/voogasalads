package game_engine.level;

import game_engine.Component;

public class LevelInfiniteComponent extends Component<Boolean>{

	public LevelInfiniteComponent(String val) {
		super(Boolean.parseBoolean(val));
	}

}
