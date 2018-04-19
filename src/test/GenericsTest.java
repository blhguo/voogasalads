package test;

import java.util.Arrays;
import java.util.List;

public class GenericsTest {
	public static void main(String[] args){
		Entity e = new Entity();
		Component<Double> health = new HealthComponent(3.0);
		Component<Integer> numJumps = new NumJumpsComponent(1);
		e.addComponent(health);
		e.addComponent(numJumps);
		System.out.println("hi");
		List<Class<? extends Component<?>>> components = Arrays.asList(HealthComponent.class, NumJumpsComponent.class);
		System.out.println(e.hasAll(components));
		e.removeComponent(NumJumpsComponent.class);
		System.out.println(e.hasAll(components));
		System.out.println(e.getComponent(HealthComponent.class).getValue());
	}
}
