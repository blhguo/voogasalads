package game_engine.event;

import java.util.List;

public class Event {
	private List<Action> myActions;
	private List<Condition> myConditions;
	public Event(List<Action> actions, List<Condition> conditions){
		myActions = actions;
		myConditions = conditions;
	}
	
	public void check(){
		
	}
	
	private void execute(){
		
	}
}
