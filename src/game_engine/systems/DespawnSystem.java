package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.DespawnComponent;
import game_engine.NullType;
import game_engine.level.Level;

public class DespawnSystem implements GameSystem {
	private static final Class<? extends Component<NullType>> DESPAWN = DespawnComponent.class;
	
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(DESPAWN);
		for (Entity e : level.getEntitiesContaining(args)) {
			level.removeEntity(e);
			System.out.println("actually got removed");
		}
	}

}
