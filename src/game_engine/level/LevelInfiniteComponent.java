package game_engine.level;

import game_engine.Component;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to be infinite
 *
 */
public class LevelInfiniteComponent extends Component<Boolean>{

	/**
	 * instantiates a new LevelInfiniteComponent with a given value indicating the attribute of finiteness in the level:
	 * true for infinite and false for finite
	 * @param val
	 */
	public LevelInfiniteComponent(String val) {
		super(Boolean.parseBoolean(val));
	}

}
