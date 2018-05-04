package authoring.component_menus;

import java.util.ResourceBundle;

import game_engine.ComponentFactory;

/**
 * @author liampulsifer
 * Creates MenuElements given their Name, Type, and Value
 */
public class MenuElementFactory {
	private static final ResourceBundle userNames = ResourceBundle.getBundle("UserFriendlyNames");
	private String[] entry;
	private ComponentFactory factory;
	private ResourceBundle bundle = ResourceBundle.getBundle("Component");
	public MenuElementFactory(){

	}

	/**www
	 *
	 * @param entry -- an array with 3 elements, ElementName, Type, and value
	 * @return A new MenuElement with the correct type and value
	 */
	public <T> MenuElement<T> getElement(String[] entry, ComponentMenu menu){
		this.entry = entry;
		factory = new ComponentFactory();
		MenuElement ret;
		String className = entry[0];
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
//		try {
//			ret.setDateable(reflections.getTypesAnnotatedWith(DataConditionable.class).
//					contains(Class.forName(bundle.getString(className))));
//			System.out.println(className + " : " + ret.isDateable());
//		} catch (Exception e){
//			System.out.println("Sorry, class machine broke");
//		}
		ret.setDateable(Boolean.parseBoolean(ResourceBundle.getBundle("Editable").getString(className)));
		return ret;
	}

	private MenuElement<String> handleFile(String className) {
		return new FileMenuElement(className, factory.createComponent(className, entry[2]));
	}

	private KeyMenuElement handleKey(String className) {
		return new KeyMenuElement(className, factory.createComponent(className, entry[2]));
	}

	private StringMenuElement handleString(String className) {
		return new StringMenuElement(className, factory.createComponent(className, entry[2]));
	}

	private BooleanMenuElement handleBoolean(String className) {
		return new BooleanMenuElement(className, factory.createComponent(className, entry[2]));
	}

	private DoubleMenuElement handleDouble(String className) {
		return new DoubleMenuElement(className, factory.createComponent(className, entry[2]));
	}
}
