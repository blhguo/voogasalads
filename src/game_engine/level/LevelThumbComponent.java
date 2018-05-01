package game_engine.level;

import game_engine.Component;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to have a thumbnail
 *
 */
public class LevelThumbComponent extends Component<String>{

	/**
	 * Instantiates a new LevelThumbComponent with a string filename for the image of the thumbnail
	 * @param arg
	 */
	public LevelThumbComponent(String arg) {
		super(arg);
	}

}
