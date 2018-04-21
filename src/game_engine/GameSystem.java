package game_engine;

/**
 * 
 * @author benhubsch
 * 
 *         The Class GameSystem.
 */
public abstract class GameSystem {

	/**
	 * This is the System subclasses primary method. They implement behavior as they desire by acting on
	 * Entity's Components.
	 *
	 * @param elapsedTime the elapsed time
	 */
	public abstract void act(double elapsedTime, Level current);
}
