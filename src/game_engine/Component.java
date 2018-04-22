package game_engine;

import authoring.component_menus.MenuElement;

public abstract class Component<T> {
	private T myValue;
	private MenuElement myMenuElement;

	public Component(T val) {
		myValue = val;
	}
	
	public void setValue(T val) {
		myValue = val;
	}
	
	public T getValue() {
		return myValue;
	}
//	
//	public void setMyMenuElement(MenuElement menu){
//		myMenuElement = menu;
//	}
//	
//	public void alert(){
//		myMenuElement.alert(this.getValue());
//	}

}
