package game_engine.components.sprite.animation;

import game_engine.Component;

/**
 * Specifies the animation file name image when the entity is jumping
 * @author Andy Nguyen, Ben Hubsch, Kevin Deng, Jeremy Chen
 *
 */
public class JumpFilenameComponent extends Component<String> {

	public JumpFilenameComponent(String val) {
		super(val);
	}

}
