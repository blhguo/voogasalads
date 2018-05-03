package game_engine.level;

import game_engine.Component;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to have a name
 *
 */
public class LevelNameComponent extends Component<String>{

	/**
	 * instantiates a new LevelNameComponent with a string representing the name of the level
	 * @param arg
	 */
	public LevelNameComponent(String arg) {
		super(arg);
	}

}
