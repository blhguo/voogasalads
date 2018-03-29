package voogasalad_callussalad;


/**
 * 
 * @author macbookair
 * The Behavior module should be able to define its own behave method, which specifies 
 * what action it chooses perform based on the implementation. A behavior does not have
 * direct access to a specific entity, but it is given access to a map of key value pairs
 * (passed as a parameter in behave) that contain state data of a given entity. This 
 * allows a given Behavior component to be able to change the position of an Entity, 
 * for instance. This data map will be passed to the behavior from the specific entity. 
 * Several simple behaviors should be predefined by the GameEngine (such as our set 
 * implementations of Move, Disappear, etc.) but multiple different types of behaviors could 
 * technically be implemented by calling a specific chain of these simple behaviors on an entity. 
 * This should be discussed with Game Authoring. The API for a given behavior is shown below.
 * This API will be called from within the Entity module, making this more of an internal API. 
 * We decided to have Behavior as a module because it allows us to predefine very general actions 
 * that can be reused.
 */
public interface BehaviorInterface {
	/**
	* Executes the specific action/result of the behavior 
	*/
	void behave(Entity entity);
}
