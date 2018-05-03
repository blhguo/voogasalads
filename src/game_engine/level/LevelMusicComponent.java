package game_engine.level;

import game_engine.Component;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Kevin Deng, Ben Hubsch
 * The purpose of this class is to allow Levels to have background music
 *
 */
public class LevelMusicComponent extends Component<String>{

	/**
	 * instantiates a new LevelMusicComponent with a string denoting the name of the music file
	 * @param arg
	 */
	public LevelMusicComponent(String arg) {
		super(arg);
	}

}
