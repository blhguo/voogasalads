package authoring.component_menus;

public class MenuElementFactory {
	private String[] entry;
	public MenuElementFactory(){

	}
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
