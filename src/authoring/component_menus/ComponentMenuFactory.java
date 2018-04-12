package authoring.component_menus;




import java.util.*;
import java.util.stream.Collectors;

public class ComponentMenuFactory {
	private static final String COMPONENT_BUNDLE = "Arguments";
	private static final String COMPONENT_DELIM  = ";";
	private static final String ATTRIBUTE_DELIM  = ",";

	private ResourceBundle myComponents;
	private MenuElementFactory factory;
	List<ComponentMenu> menuList;

	public ComponentMenuFactory(){
		factory = new MenuElementFactory();
		myComponents = ResourceBundle.getBundle(COMPONENT_BUNDLE);
		menuList = new ArrayList<>();
		for (String component : myComponents.keySet()) {
			String[] attributes = myComponents.getString(component).split(COMPONENT_DELIM);
			//Arrays.stream(attributes).forEach(str ->System.out.println(str));
			menuList.add(newComponentMenu(attributes, component));
		}
	}
	public ComponentMenu newComponentMenu(String[] attributes, String component){

		ComponentMenu newMenu = new ComponentMenu(component);
		for (String attr : attributes) {
			String[] attrSplit = attr.split(ATTRIBUTE_DELIM);
			newMenu.addMenuElement(factory.getElement(attrSplit));
		}
		return newMenu;
	}

	public List<ComponentMenu> getMenus() {
		return menuList;
		//return menuList.stream().map(menu -> new TitledPane(menu.getType()0, menu)).collect(Collectors.toList());
	}
}
