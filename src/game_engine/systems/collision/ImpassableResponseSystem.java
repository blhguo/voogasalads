package game_engine.systems.collision;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import game_engine.Component;
import game_engine.Entity;
import game_engine.components.collision.CollidedComponent;
import game_engine.components.collision.PassableComponent;
import game_engine.components.collision.PushableComponent;
import game_engine.components.collision.edge_collided.BottomCollidedComponent;
import game_engine.components.collision.edge_collided.LeftCollidedComponent;
import game_engine.components.collision.edge_collided.RightCollidedComponent;
import game_engine.components.collision.edge_collided.TopCollidedComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.level.Level;

/**
 * @author: Jeremy Chen A GameSystem that provides generic behavior for entities that posses a
 *          CollidedComponent (have been collided) Describes very basic collision behavior (stopping
 *          & pushing)
 * 
 */
public class ImpassableResponseSystem extends CollisionResponseSystem {
	private static final Class<? extends Component<List<Entity>>> TOP = TopCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> BOTTOM = BottomCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> RIGHT = RightCollidedComponent.class;
	private static final Class<? extends Component<List<Entity>>> LEFT = LeftCollidedComponent.class;
	private static final Class<? extends Component<Boolean>> PASSABLE = PassableComponent.class;

	/*
	 * (non-Javadoc)
	 * 
	 * @see game_engine.GameSystem#act(double) Main loop: checks for matching velocity/collision
	 * direction, as to stop/push entities in appropraite cases
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Entity> collidedEntities = getCollidedEntities(level);
		List<Entity> impassibleEntities = level.getEntitiesContaining(collidedEntities, Arrays.asList(PASSABLE))
				.stream()
				.filter(e -> !(e.getComponent(PASSABLE).getValue()))
				.collect(Collectors.toList());
		
		for (Entity e : impassibleEntities) {
			XVelComponent xv = (XVelComponent) (e.getComponent(XVelComponent.class));
			YVelComponent yv = (YVelComponent) (e.getComponent(YVelComponent.class));

			if (xv != null && ((e.getComponent(LEFT) != null && xv.getValue() < 0)
					|| (e.getComponent(RIGHT) != null && xv.getValue() > 0))) {
				xv.setValue(0.0);
			}
			if (yv != null && ((e.getComponent(BOTTOM) != null && yv.getValue() > 0)
					|| (e.getComponent(TOP) != null && yv.getValue() < 0))) {	
				yv.setValue(0.0);
			}
		}
	}
	
	
}
