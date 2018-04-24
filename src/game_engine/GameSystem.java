package game_engine;

/**
 * 
 * @author benhubsch
 * 
 *         The Class GameSystem.
 */
public abstract class GameSystem {

	protected Engine myEngine;

	/**
	 * Instantiates a new GameSystem object.
	 *
	 * @param engine the engine
	 */
	public GameSystem(Engine engine) {
		myEngine = engine;
	}

	protected Engine getEngine() {
		return myEngine;
	}

	/**
	 * This is the System subclasses primary method. They implement behavior as they desire by acting on
	 * Entity's Components.
	 *
	 * @param elapsedTime the elapsed time
	 */
	public abstract void act(double elapsedTime);
//	
//	protected Double getDoubleValue(Entity e, Class componentClass) {
//		Component c = e.getComponent(componentClass);
//		if(c!=null) {
//			return Double.parseDouble(c.getValue());
//		}
//		return null;
//	}
//	
//	protected Boolean getBooleanValue(Entity e, Class componentClass) {
//		Component c = e.getComponent(componentClass);
//		if(c!=null) {
//			return Boolean.parseBoolean(c.getValue());
//		}
//		return null;
//	}
//	
//	protected Integer getIntegerValue(Entity e, Class componentClass) {
//		Component c = e.getComponent(componentClass);
//		if(c!=null) {
//			return Integer.parseInt(c.getValue());
//		}
//		return null;
//	}
}
