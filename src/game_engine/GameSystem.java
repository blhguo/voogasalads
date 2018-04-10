package game_engine;

public abstract class GameSystem {
	
	protected Engine myEngine;
	
	public GameSystem(Engine engine) {
		myEngine = engine;
	}
	
	protected Engine getEngine() {
		return myEngine;
	}
	
	public abstract void act(double elapsedTime);

}
