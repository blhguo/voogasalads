package test;

import java.util.Set;

import org.reflections.Reflections;

import game_engine.event.conditions.DataConditionable;

public class Tester {
	public static void main(String args[]) {
		Reflections reflections = new Reflections("game_engine.components");
		Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(DataConditionable.class);
		
		for (Class<?> clazz : annotated) {
			System.out.println(clazz.getSimpleName().replace("Component", ""));
		}
	}
}
