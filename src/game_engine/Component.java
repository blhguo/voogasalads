package game_engine;

import authoring.component_menus.MenuElement;
import javafx.beans.property.Property;
import observables.Subject;

import java.beans.PropertyChangeEvent;


public abstract class Component<T> {
	private T myValue;
	private MenuElement myMenuElement;
	public void setMyMenuElement(MenuElement menu){
		myMenuElement = menu;
	}
	public Component(T val) {
		myValue = val;
	}

	public void setValue(T val) {
		myValue = val;
		alert();
	}
	
	public T getValue(){
		return myValue;
	}

	public void alert(){
		myMenuElement.alert(this.getValue());
	}
}

