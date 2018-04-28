package game_engine;

import game_engine.components.HealthComponent;
import game_engine.components.PrimeComponent;
import game_engine.components.sprite.FilenameComponent;

public class DeepCopyTester {
	Entity e1;
	Entity e2;
	
	private HealthComponent hc;
	private PrimeComponent pc;
	private FilenameComponent fc;
	
	public DeepCopyTester(){
		e1 = new Entity();
		hc = new HealthComponent("100");
		pc = new PrimeComponent();
		fc = new FilenameComponent("original");
		
		e1 = new Entity();
	}
	
}
