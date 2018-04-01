package voogasalad_callussalad;

/**
 * 
 * @author GameEngine
 * The Entity represents any visible (or invisible), interactive game element in a level. 
 * The Entity module heavily employs composition (over inheritance) as to maximize backwards 
 * compatibility, and to minimize massive refactoring in the front-end modules when engine 
 * adds new features. The Entity will contain passive attributes in an instance of the Attribute
 * class. This will be an abstraction of a map that includes fields such as, X position, Y position,
 * sprite image, etc. It will also contain a collection of instances of the Behavior class. This 
 * collection describes the available actions (essentially Events) available to be activated in the Entity. 
 * The below interface describes the interface that Authoring can interact with to allow the user to construct
 * and edit these two key fields of an Entity. (a similar, but read-only interface may be implemented for 
 * Player to interface with)
 *
 */
public interface EntityInterface {
	/**
	 * activates a behavior on the entity based on the behavior's ID
	 */
	void activate(String ID);

	/**
	 * Adds a behavior to the entity
	 */
	void addBehavior(Behavior behavior);

	/**
	 * Removes a behavior
	 */
	void removeBehavior(Behavior behavior);

	/**
	 * Adds an attribute to the entity
	 */
	void addAttribute(Attribute attr);

	/**
	 * Removes an attribute
	 */
	void removeAttribute(Attribute attr);
}
