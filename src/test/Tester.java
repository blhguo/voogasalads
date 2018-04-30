package test;

import game_engine.event.conditions.DataConditionable;
import org.reflections.Reflections;

import java.util.Set;

public class Tester {
	
	private static final String COMPONENT_FACTORY = "game_engine.components";
	private static final String SUFFIX = "Component";
	public static final Reflections reflections = new Reflections(COMPONENT_FACTORY);
	public static void main(String args[]) {

		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(DataConditionable.class);
		
		for (Class<?> clazz : annotated) {
			System.out.println(clazz.getSimpleName().replace(SUFFIX, ""));
		}
	}
}
