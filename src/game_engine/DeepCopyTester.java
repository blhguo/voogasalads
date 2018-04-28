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
		e1.addComponent(hc);
		e1.addComponent(pc);
		e1.addComponent(fc);
		
		e2=e1.copy();
	}
	
	public void run() {
		System.out.println("INITIAL STATES\n");
		System.out.println("ENTITY 1");
		printComponents(e1);
		System.out.println("ENTITY 2 (copy)");
		printComponents(e2);
		
		hc.setValue(69.0);
		fc.setValue("yoloswag");
		
		System.out.println("POST STATES\n");
		System.out.println("ENTITY 1 (changed");
		printComponents(e1);
		System.out.println("ENTITY 2 (shouldn't be)");
		printComponents(e2);
	}
	
	public void printComponents(Entity e) {
		for(Component<?> c: e.getComponents()) {
			System.out.println(c.getClass() + " : " + c.getValue());
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		DeepCopyTester d = new DeepCopyTester();
		d.run();
	}
	
}
