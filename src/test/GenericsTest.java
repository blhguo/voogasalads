package test;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Entity;
import game_engine.components.HealthComponent;
import game_engine.components.NumberOfJumpsAllowedComponent;

public class GenericsTest {
	public static void main(String[] args){
		Entity e = new Entity();
		Component<Double> health = new HealthComponent("3");
		Component<Double> numJumps = new NumberOfJumpsAllowedComponent("1");
		e.addComponent(health);
		e.addComponent(numJumps);
		System.out.println("hi");
		List<Class<? extends Component<?>>> components = Arrays.asList(HealthComponent.class, NumberOfJumpsAllowedComponent.class);
		System.out.println(e.hasAll(components));
		e.removeComponent(NumberOfJumpsAllowedComponent.class);
		System.out.println(e.hasAll(components));
		System.out.println(e.getComponent(HealthComponent.class).getValue());
	}
}
