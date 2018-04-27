package authoring.component_menus;

import game_engine.Component;
import game_engine.ComponentFactory;

import java.awt.*;

/**
 * @author liampulsifer
 * Creates MenuElements given their Name, Type, and Value
 */
public class MenuElementFactory {
	private String[] entry;
	private ComponentFactory factory;
	public MenuElementFactory(){

	}

	/**
	 *
	 * @param entry -- an array with 3 elements, ElementName, Type, and value
	 * @return A new MenuElement with the correct type and value
	 */
	public MenuElement getElement(String[] entry, ComponentMenu menu){
		this.entry = entry;
		factory = new ComponentFactory();
		MenuElement ret;
		if(entry[1].equals("d")){
			ret = handleDouble();
		}
		else if(entry[1].equals("b")){
			ret = handleBoolean();
		}
		else if(entry[1].equals("k")){
			ret = handleKey();
		}
		else if(entry[1].equals("f")){
			ret = handleFile();
		}
		else {
			ret = handleString();
		}
		ret.setMyMenu(menu);
		return ret;
	}

	private MenuElement handleFile() {
		return new FileMenuElement(entry[0], factory.createComponent(entry[0], entry[2]));
	}

	private KeyMenuElement handleKey() {
		return new KeyMenuElement(entry[0], factory.createComponent(entry[0], entry[2]));
	}

	private StringMenuElement handleString() {
		return new StringMenuElement(entry[0], factory.createComponent(entry[0], entry[2]));
	}

	private BooleanMenuElement handleBoolean() {
		return new BooleanMenuElement(entry[0], factory.createComponent(entry[0], entry[2]));
	}

	private NumberMenuElement handleDouble() {
		return new NumberMenuElement(entry[0], factory.createComponent(entry[0], entry[2]));
	}
}
