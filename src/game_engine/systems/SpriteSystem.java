package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.physics.XVelComponent;
import game_engine.components.sprite.SpritePolarityComponent;
import game_engine.level.Level;

/**
 * @author Jeremy Chen, Kevin Deng
 * The purpose of this class is to change the polarity (direction that the sprite is facing) of a given entity
 * depending on the sign of its x-velocity.
 */
public class SpriteSystem implements GameSystem {
	private static final Class<? extends Component<Double>> POLARITY = SpritePolarityComponent.class;
	private static final Class<? extends Component<Double>> X_VEL = XVelComponent.class;

	/**
	 * sets the polarity of a given entity based off of its x-velocity
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(POLARITY, X_VEL);
		for(Entity e : level.getEntitiesContaining(args)) {
			setPolarity(e);
		}
	}
	
	private void setPolarity(Entity e){
		double xVel = e.getComponent(X_VEL).getValue();
		double polarity = e.getComponent(POLARITY).getValue();
		if(xVel*polarity < 0){
			e.getComponent(POLARITY).setValue(-1*polarity);
		}
	}

}
