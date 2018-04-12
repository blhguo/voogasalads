package authoring.component_menus;

/**
 * Creates MenuElements given their Name, Type, and Value
 */
public class MenuElementFactory {
	private String[] entry;
	public MenuElementFactory(){

	}

	/**
	 *
	 * @param entry -- an array with 3 elements, ElementName, Type, and value
	 * @return A new MenuElement with the correct type and value
	 */
	public MenuElement getElement(String[] entry){
		this.entry = entry;
		MenuElement ret;
		if(entry[1].equals("d")){
			ret = handleDouble();
		}
		else if(entry[1].equals("b")){
			ret = handleBoolean();
		}
		else {
			ret = handleString();
		}
		return ret;
	}

	private StringMenuElement handleString() {
		return new StringMenuElement(entry[0], entry[2]);
	}

	private BooleanMenuElement handleBoolean() {
		return new BooleanMenuElement(entry[0], Boolean.parseBoolean(entry[2]));
	}

	private NumberMenuElement handleDouble() {
		return new NumberMenuElement(entry[0], entry[2]);
	}
}
