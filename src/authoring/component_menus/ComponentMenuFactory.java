package authoring;

import game_engine.Component;
import game_engine.components.Collidable;

public class ComponentMenuFactory {

	public ComponentMenuFactory(){

	}
	public ComponentMenu newComponentMenu(String type){
		if (type.equals("Collidable")){
			return new CollidableMenu();
		}
		else {
			//TODO Make this throw or try/catch an exception, not sure yet
			return null;
		}
	}

}
