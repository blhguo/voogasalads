package game_engine.event;

import java.util.ArrayList;
import java.util.List;

public class Event implements AuthorableEvent, PlayableEvent{
	private List<Action> myActions;
	private List<Condition> myConditions;
	public Event(List<Action> actions, List<Condition> conditions){
		myActions = actions;
		myConditions = conditions;
	}
	public Event(){
		myActions = new ArrayList<>();
		myConditions = new ArrayList<>();
	}
	
	@Override
	public void occur() {
		for (Condition condition : myConditions) {
			if (!condition.evaluate()) {
				return;
			}
		}
		for (Action action : myActions) {
			action.execute();
		}
	}
	
	@Override
	public void addAction(Action action) {
		myActions.add(action);
	}
	
	@Override
	public void addCondition(Condition condition) {
		myConditions.add(condition);
	}
	
	@Override
	public List<Action> getActions(){
		return myActions;
	}
	
	@Override
	public List<Condition> getConditions(){
		return myConditions;
	}
}
