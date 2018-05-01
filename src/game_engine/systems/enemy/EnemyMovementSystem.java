package game_engine.systems.enemy;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.level.Level;

/**
 * 
 * @author Ben Hubsch
 * The purpose of this class is to accommodate for the feature of having a pacing enemy within a game.
 *
 */
public abstract class EnemyMovementSystem implements GameSystem {
	
	private Class<? extends Component<Double>> myDefaultTime;
	private Class<? extends Component<Double>> myCurrTime;
	private Class<? extends Component<Double>> myCurrVel;
	
	/**
	 * instantiates a new EnemyMovementSystem with a given default time, current time, and current velocity
	 * @param defaultTime
	 * @param currTime
	 * @param currVel
	 */
	public EnemyMovementSystem(Class<? extends Component<Double>> defaultTime, Class<? extends Component<Double>> currTime, Class<? extends Component<Double>> currVel) {
		myDefaultTime = defaultTime;
		myCurrTime = currTime;
		myCurrVel = currVel;
	}

	/**
	 * paces an enemy entity back and forth based off of the given time attributes of this system
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(myDefaultTime, myCurrTime, myCurrVel);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<Double> defaultTime = entity.getComponent(myDefaultTime);
			Component<Double> currTime = entity.getComponent(myCurrTime);
			Component<Double> currVel = entity.getComponent(myCurrVel);
			currTime.setValue(currTime.getValue() + elapsedTime);
			
			if (currTime.getValue() >= defaultTime.getValue()) {
				currTime.setValue(0.0);
				currVel.setValue(-1 * currVel.getValue());
			}
		}
	}

}
