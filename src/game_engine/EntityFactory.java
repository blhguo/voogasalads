package game_engine;

/**
 * 
 * @author benhubsch
 * 
 * A factory for creating Entity objects.
 */
public class EntityFactory {

	/**
	 * Creates a new Entity object.
	 *
	 * @return the entity
	 */
	public Entity createEntity() {
		return new Entity();
	}

}
