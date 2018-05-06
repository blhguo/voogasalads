package game_engine.event;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Andy Nguyen, Ben Hubsch
 * The purpose of this class is to provide a modular way for users to create in-game events that follow outside the
 * realm of default game behavior. The Event class is meant to be Authorable in the sense that they can be created/added to
 * during runtime in the Authoring Environment. They are meant to be Playable in the sense that they can be evaluated in the
 * Player environment. An Event is an object that performs a given list of actions once all of its conditions is met.
 * One example of a simple Event that a user could define is having a level change occur (Action) once Mario reaches a checkpoint (Condition).
 *
 */
public class Event implements AuthorableEvent, PlayableEvent {
	private List<Action> myActions;
	private List<Condition> myConditions;
	/**
	 * Instantiates an Event with preset actions and conditions
	 * @param actions
	 * @param conditions
	 */
	public Event(List<Action> actions, List<Condition> conditions) {
		myActions = actions;
		myConditions = conditions;
	}
	
	/**
	 * Instantiates an empty Event under the assumption that actions and conditions will get added dynamically during authoring
	 */
	public Event() {
		myActions = new ArrayList<>();
		myConditions = new ArrayList<>();
	}
	
	/**
	 * The Event class is composed of a list of conditions and a list of actions. An event executes
	 * all of its actions only when every condition in its list of conditions is met
	 */
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
	
	/**
	 * adds an action to the list of actions
	 */
	@Override
	public void addAction(Action action) {
		myActions.add(action);
	}
	
	/**
	 * adds a condition to the list of conditions
	 */
	@Override
	public void addCondition(Condition condition) {
		myConditions.add(condition);
	}
	
	/**
	 * returns the current list of actions in the event
	 */
	@Override
	public List<Action> getActions(){
		return myActions;
	}
	
	/**
	 * returns the current list of conditions in the event
	 */
	@Override
	public List<Condition> getConditions(){
		return myConditions;
	}
}
