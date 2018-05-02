package game_engine.event;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * Classes that implement the Action interface are meant to perform a given action within their execute method. The
 * purpose of this interface is to help with the creation/execution of events. In events in a general game, there exist
 * a set of triggers for an event to happen, as well as an action triggered during the event. This interface encompasses
 * the action triggered during an event. It provides a uniform way for specific Actions (classes that implement this interface)
 * to be called.
 *
 */
public interface Action {
	/**
	 * this method executes an action to be performed during an event
	 */
	void execute();
}
