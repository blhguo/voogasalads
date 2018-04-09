package authoring.component_menus;

import authoring.component_menus.CollidableMenu;
import authoring.component_menus.ComponentMenu;

public class ComponentMenuFactory {

	public ComponentMenuFactory(){

	}
	public ComponentMenu newComponentMenu(String type){
		if (type.equals("Collidable")){
			return new CollidableMenu();
		}
		else if (type.equals("Image")){
			return new ImageMenu();
		}
		else {
			//TODO Make this throw or try/catch an exception, not sure yet
			System.out.println("Tried to add a null in the Factory");
			return null;
		}
	}

}
