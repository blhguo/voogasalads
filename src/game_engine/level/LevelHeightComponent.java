package game_engine.level;

import game_engine.Component;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to have a height attribute
 *
 */
public class LevelHeightComponent extends Component<Double>{
	/**
	 * instantiates a new LevelHeightComponent with the value for the Level's height
	 * @param val
	 */
	public LevelHeightComponent(Double val) {
		super(val);
	}

}
