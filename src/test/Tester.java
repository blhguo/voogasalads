package test;

import java.util.Set;

import org.reflections.Reflections;

import game_engine.event.conditions.DataConditionable;

public class Tester {
	
	private static final String COMPONENT_FACTORY = "game_engine.components";
	private static final String SUFFIX = "Component";
	
	public static void main(String args[]) {
		Reflections reflections = new Reflections(COMPONENT_FACTORY);
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(DataConditionable.class);
		
		for (Class<?> clazz : annotated) {
			System.out.println(clazz.getSimpleName().replace(SUFFIX, ""));
		}
	}
}
