package game_engine.event;

import java.util.ArrayList;
import java.util.List;

public class Event {
	private List<Action> myActions;
	private List<Condition> myConditions;
	public Event(){
		myActions = new ArrayList<>();
		myConditions = new ArrayList<>();
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

	public void addCondition(Condition condition){
		myConditions.add(condition);
	}
	public void removeCondition(Condition condition){
		myConditions.remove(condition);
	}
	public void addAction(Action action){
		myActions.add(action);
	}
	public void removeAction(Action action){
		myActions.remove(action);
	}
}
