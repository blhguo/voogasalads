package engine;

public interface Entity {
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
