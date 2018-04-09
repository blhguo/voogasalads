package test;

import java.util.Arrays;

import game_engine.ComponentFactory;
import game_engine.Entity;
import game_engine.EntityFactory;
import game_engine.components.PositionComponent;

public class Test {
	public static void main(String[] args) {
		EntityFactory ef = new EntityFactory();
		ComponentFactory cf = new ComponentFactory();
		Entity entity = ef.createEntity();
		cf.addComponent(entity, "Position", Arrays.asList("5", "5"));
		cf.addComponent(entity, "Position", Arrays.asList("6", "10"));
		
//		Class<Position> cp = Position.class;
//		cp.getConstructor()
	}
}
