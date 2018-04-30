package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.TimerComponent;
import game_engine.level.Level;

public class TimerSystem implements GameSystem {
	private static final Class<? extends Component<Double>> TIMER = TimerComponent.class;
	
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
