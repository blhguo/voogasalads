package game_engine.event;

import java.util.List;

/**
 * @author Jeremy Chen
 *	A write-enabled interface that allows Authoring to create and have full editing
 *	privileges over Events
 */
public interface AuthorableEvent {
	public void addAction(Action action);
	
	public void addCondition(Condition condition);
	
	public List<Action> getActions();
	
	public List<Condition> getConditions();
}
