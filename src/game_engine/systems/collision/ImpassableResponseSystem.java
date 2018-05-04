package game_engine.systems.collision;

import java.util.ArrayList;
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
	private static final Class<? extends Component<Boolean>> PASSABLE = PassableComponent.class;
	private static final double STOP = 0.0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see game_engine.GameSystem#act(double) Main loop: checks for matching velocity/collision
	 * direction, as to stop/push entities in appropriate cases
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Entity> collidedEntities = getCollidedEntities(level);
		List<Entity> passibleEntities = level.getEntitiesContaining(collidedEntities, Arrays.asList(PASSABLE));


		List<Entity> impassibleEntities = new ArrayList<Entity>();
		for(Entity e: passibleEntities){
			if(!e.getComponent(PASSABLE).getValue()){
				impassibleEntities.add(e);
			}
		}
		
		for (Entity e : impassibleEntities) {
			XVelComponent xv = (XVelComponent) (e.getComponent(XVelComponent.class));
			YVelComponent yv = (YVelComponent) (e.getComponent(YVelComponent.class));

			if (xv != null){
				boolean leftImpassible = xv.getValue() < 0 && otherHasImpassible(e, LEFT);
				boolean rightImpassible = xv.getValue() > 0 && otherHasImpassible(e, RIGHT);
				if(leftImpassible || rightImpassible){
					xv.setValue(STOP);
				}
			}
			if (yv != null){
				boolean bottomImpassible = yv.getValue() > 0 && otherHasImpassible(e, BOTTOM);
				boolean topImpassible = yv.getValue() < 0 && otherHasImpassible(e, TOP);
				if(bottomImpassible || topImpassible){
					yv.setValue(STOP);
				}
			}
		}
	}

	/**
	 * Helper method that checks if there exists an impassible entity that this entity has collided with
	 * @param e
	 * @param cc
	 * @return
	 */
	private boolean otherHasImpassible(Entity e, Class<? extends Component<List<Entity>>> cc){
		Component<List<Entity>> collidedComponent = e.getComponent(cc);
		if(collidedComponent == null){
			return false;
		}
		for(Entity other: collidedComponent.getValue()){
			Component<Boolean> passableComponent = other.getComponent(PASSABLE);
			if(passableComponent !=null && !passableComponent.getValue()){
				return true;
			}
		}
		return false;
	}
}
