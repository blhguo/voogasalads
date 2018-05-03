package game_engine.level;

import game_engine.Component;

/**
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to have vertical scrolling ability if it wants
 *
 */
public class LevelVScrollComponent extends Component<Boolean>{

	/**
	 * instantiates a new LevelHScrollComponent with the value for vertical scrolling, false for no vertical scrolling
	 * capabilities and true for vertical scrolling
	 * @param val
	 */
	public LevelVScrollComponent(Boolean val){
		super(val);
	}
	
}
