package game_engine.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game_engine.Component;
import game_engine.Entity;
import game_engine.components.Physics;

public class EntityTest {
	
	@Test
	public void testComponentAdded() {
		Entity e = new Entity();
		Component c = new Physics(0, 0, 0);
		e.addComponent(c);
		assertEquals(c, e.getComponent(Physics.class));
	}
}
