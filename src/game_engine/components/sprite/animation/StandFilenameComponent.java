package game_engine.components.sprite.animation;

import game_engine.Component;

/**
 * Specifies the file name of an image when the entity is standing still
 * @author Andy Nguyen, Ben Hubsch, Kevin Deng, Jeremy Chen
 *
 */
public class StandFilenameComponent extends Component<String> {

	public StandFilenameComponent(String val) {
		super(val);
	}

}
