package game_engine.level;

import game_engine.Component;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to have ability to have a background image
 *
 */
public class LevelBackgroundComponent extends Component<String>{
	/**
	 * instantiates a new LevelBackgroundComponent with the given filename for the background image for the level
	 * @param val
	 */
	public LevelBackgroundComponent(String val) {
		super(val);
	}

}
