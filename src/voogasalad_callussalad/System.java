package voogasalad_callussalad;

public abstract class System {
	
	protected Engine myEngine;
	
	public System(Engine engine) {
		myEngine = engine;
	}
	
	protected Engine getEngine() {
		return myEngine;
	}

}
