package game_engine.event;

/**
 * @author Jeremy Chen
 *	A read-only interface that closes the Event's actions and conditions to Engine and Player
 *	
 */
public interface PlayableEvent {
	public void occur();
}
