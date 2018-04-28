package game_engine.event;

/**
 * @author Jeremy Chen
 *
 */

public abstract class ActionFactory {
	private static final String ACTION_BUNDLE = "Action";
	
	public abstract Action createAction();
}
