package game_engine;

/**
 * The Interface Component is just a flag interface.
 * Example: http://www.w3processing.com/index.php?subMenuLoad=java/oop/InterfaceFlag.php
 */
public abstract class Component {
	private String myValue;
	
	public Component(String arg){
		
	}
	
	public String getValue(){
		return myValue;
	}
	
	public void setValue(String value){
		myValue = value;
	}
}
