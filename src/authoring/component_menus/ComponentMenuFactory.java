package authoring.component_menus;




import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author liampulsifer
 * Makes new Component menus, either from the resource file (default)
 * or from existing components (using the Component's getValues() method
 */
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

	/**
	 *
	 * @param attributes string array of possible attributes of the title. Each attribute contains a
	 *                   comma-separated list of Attribute,Type,Default-value
	 * @param title Name of title
	 * @return
	 */
	public ComponentMenu newComponentMenu(String[] attributes, String title){
		ComponentMenu newMenu = new ComponentMenu(title);
		for (String attr : attributes) {
			String[] attrSplit = attr.split(ATTRIBUTE_DELIM);
			newMenu.addMenuElement(factory.getElement(attrSplit, newMenu));
		}
		return newMenu;
	}

	/**
	 *
	 * @return the list of ComponentMenus created in the default implementation
	 */
	public List<ComponentMenu> getDefaultMenus() {
		return new ArrayList<>(menuList);
		//return menuList.stream().map(menu -> new TitledPane(menu.getType()0, menu)).collect(Collectors.toList());
	}
}
