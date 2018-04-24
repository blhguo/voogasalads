package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import game_engine.Engine;
import game_engine.GameSystem;

public class Tester {
	public static void main(String args[]) {
		Reflections reflections = new Reflections("game_engine", new SubTypesScanner(true));
		Set<Class<? extends GameSystem>> allClasses = reflections.getSubTypesOf(GameSystem.class);

		List<GameSystem> systems = new ArrayList<>();

		allClasses.stream().filter(clazz -> !Modifier.isAbstract(clazz.getModifiers())).forEach(clazz -> {
			try {
				Parameter[] params = clazz.getDeclaredConstructors()[0].getParameters();
				Constructor<?> ctor;
				GameSystem system;
				if (params.length > 0) {
					ctor = clazz.getDeclaredConstructor(new Class[] { Engine.class });
					system = (GameSystem) ctor.newInstance(new Engine());
				} else {
					ctor = clazz.getDeclaredConstructor(new Class[] {});
					system = (GameSystem) ctor.newInstance();
				}
				systems.add(system);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		System.out.println(systems);

	}
}
