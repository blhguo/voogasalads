package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.TimerComponent;
import game_engine.level.Level;

/**
 * 
 * @author Andy Nguyen
 * The purpose of this system is to find all entities containing the TimerComponent and add
 * the elapsed time to the value of the TimerComponent in that entity. The purpose for this is to
 * allow for the declaration of timed events/actions/conditions that an author can define in a game. 
 *
 */
public class TimerSystem implements GameSystem {
	private static final Class<? extends Component<Double>> TIMER = TimerComponent.class;
	
	/**
	 * adds the elapsed time to the timer of entities containing the TimerComponent.
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(TIMER);
		for (Entity e : level.getEntitiesContaining(args)) {
			Component<Double> timer = e.getComponent(TIMER);
			double nextTime = timer.getValue() + elapsedTime;
			timer.setValue(nextTime);
		}
	}

}
