package game_engine.components.collision.hitbox;

import java.util.List;

import game_engine.Component;


/**
 * @author Jeremy Chen
 * 
 * A list of tuples, representing 2D points, defined by relative position from center of hitbox
 *
 */
@Deprecated
public class GJKHitboxComponent extends Component<List<Double[]>> {

	public GJKHitboxComponent(List<Double[]> val) {
		super(val);
	}

}
