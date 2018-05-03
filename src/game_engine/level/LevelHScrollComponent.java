package game_engine.level;

import game_engine.Component;

/**
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to have horizontal scrolling ability if it wants
 *
 */
public class LevelHScrollComponent extends Component<Boolean>{

	/**
	 * instantiates a new LevelHScrollComponent with the value for horizontal scrolling, false for no horizontal scrolling
	 * capabilities and true for horizontal scrolling
	 * @param val
	 */
	public LevelHScrollComponent(Boolean val) {
		super(val);
	}

}
