package game_engine;

import game_engine.level.Level;

/**
 * 
 * @author benhubsch
 * 
 *         The Class GameSystem.
 */
public interface GameSystem {

	/**
	 * This is the System subclasses primary method. They implement behavior as they desire by acting on
	 * Entity's Components.
	 *
	 * @param elapsedTime the elapsed time
	 */
	public abstract void act(double elapsedTime, Level level);
}
