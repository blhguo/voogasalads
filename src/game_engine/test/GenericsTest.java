package game_engine.test;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.components.HealthComponent;
import game_engine.components.NumberOfJumpsAllowedComponent;

public class GenericsTest {
	
	public static void main(String[] args) {
		System.out.println("Hello");
//		Entity e = new Entity();
//		Component<Double> health = new HealthComponent(3.0);
//		Component<Double> numJumps = new NumberOfJumpsAllowedComponent(1.0);
//		e.addComponent(health);
//		e.addComponent(numJumps);
//		List<Class<? extends Component<?>>> components = Arrays.asList(HealthComponent.class, NumberOfJumpsAllowedComponent.class);
//		//System.out.println(e.hasAll(components));
//		//e.removeComponent(HealthComponent.class);
//		//System.out.println(e.hasAll(components));
//		System.out.println(e.getComponent(HealthComponent.class).getValue());
		
	}

}
