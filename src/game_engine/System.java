package game_engine;

public abstract class System {
	
	protected Engine myEngine;
	
	public System(Engine engine) {
		myEngine = engine;
	}
	
	protected Engine getEngine() {
		return myEngine;
	}
	
	public abstract void act(double elapsedTime);

}
