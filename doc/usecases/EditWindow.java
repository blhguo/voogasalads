package voogasalad_callussalad;

/**
 * Use case: The user is able to place an object in the game environment
 * This use case assumes the entity being added to the edit window has already been created and specified in the 
 * Entity Manager. This use case just details what would happen when an entity is dragged onto the edit window to be 
 * added to the level. The Entities are able to draw themselves, so once the entity is dropped onto the edit window, 
 * addEntity is called and the Entity draws itself on a given Group. 
 */

public class EditWindow {
	Group root;
	
	public EditWindow(){
		root = new Group();
	}
	
	private void addEntity(Entity e){
		e.draw(root);
	}
	
}
