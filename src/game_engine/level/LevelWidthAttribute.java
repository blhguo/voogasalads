package game_engine.level;

import game_engine.Component;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to have a width attribute
 *
 */
public class LevelWidthAttribute extends Component<Double>{

	/**
	 * instantiates a new LevelWidthAttribute with a double indicating the width of the level
	 * @param arg
	 */
	public LevelWidthAttribute(String arg) {
		super(Double.parseDouble(arg));
	}

}
