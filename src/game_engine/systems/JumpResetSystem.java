package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.DefaultNumberOfJumpsComponent;
import game_engine.components.NumberOfJumpsAllowedComponent;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.level.Level;

public class JumpResetSystem extends GameSystem{
	private static final Class<? extends Component<Double>> DEFAULT_JUMPS = DefaultNumberOfJumpsComponent.class;
	private static final Class<? extends Component<Double>> NUM_JUMPS_ALLOWED = NumberOfJumpsAllowedComponent.class;
	private static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
	
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(DEFAULT_JUMPS, NUM_JUMPS_ALLOWED, BOTTOM);
		for (Entity e : level.getEntitiesContaining(args)) {
			Component<Double> defaultJumps = e.getComponent(DEFAULT_JUMPS);
			Component<Double> numJumpsAllowed = e.getComponent(NUM_JUMPS_ALLOWED);
			Component<List<Entity>> bottomCollided = e.getComponent(BOTTOM);
			if(bottomCollided.getValue().size() > 0){
				numJumpsAllowed.setValue(defaultJumps.getValue());
			}
		}
	}

}
