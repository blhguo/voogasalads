package game_engine.event;

import java.util.List;

public class Event {
	private List<Action> myActions;
	private List<Condition> myConditions;
	public Event(List<Action> actions, List<Condition> conditions){
		myActions = actions;
		myConditions = conditions;
	}
	
	public void occur() {
		for (Condition condition : myConditions) {
			if (! condition.evaluate()) {
				return;
			}
		}

		for (Action action : myActions) {
			action.execute();
		}
	}
}
