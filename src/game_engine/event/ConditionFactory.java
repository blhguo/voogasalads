package game_engine.event;

/**
 * @author Jeremy Chen
 *
 */
public abstract class ConditionFactory {
	private static final String CONDITION_BUNDLE = "Condition";
	
	public abstract Condition createCondition();

}
