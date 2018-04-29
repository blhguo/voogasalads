package authoring.component_menus;

import game_engine.Component;
import game_engine.ComponentFactory;

import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author liampulsifer
 * Creates MenuElements given their Name, Type, and Value
 */
public class MenuElementFactory {
	private static final ResourceBundle userNames = ResourceBundle.getBundle("UserFriendlyNames");
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
		String className = entry[0];
		entry[0] = userNames.getString(entry[0]);
		if(entry[1].equals("d")){
			ret = handleDouble(className);
		}
		else if(entry[1].equals("b")){
			ret = handleBoolean(className);
		}
		else if(entry[1].equals("k")){
			ret = handleKey(className);
		}
		else if(entry[1].equals("f")){
			ret = handleFile(className);
		}
		else {
			ret = handleString(className);
		}
		ret.setMyMenu(menu);
		return ret;
	}

	private MenuElement handleFile(String className) {
		return new FileMenuElement(entry[0], factory.createComponent(className, entry[2]));
	}

	private KeyMenuElement handleKey(String className) {
		return new KeyMenuElement(entry[0], factory.createComponent(className, entry[2]));
	}

	private StringMenuElement handleString(String className) {
		return new StringMenuElement(entry[0], factory.createComponent(className, entry[2].toString()));
	}

	private BooleanMenuElement handleBoolean(String className) {
		return new BooleanMenuElement(entry[0], factory.createComponent(className, entry[2]));
	}

	private NumberMenuElement handleDouble(String className) {
		return new NumberMenuElement(entry[0], factory.createComponent(className, entry[2]));
	}
}
