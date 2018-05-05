package game_engine.components.sprite.animation;

import game_engine.Component;

/**
 * Specifies the file name of an image when the entity is running
 * @author Andy Nguyen, Ben Hubsch, Kevin Deng, Jeremy Chen
 *
 */
public class RunFilenameComponent extends Component<String> {

	public RunFilenameComponent(String val) {
		super(val);
	}

}
