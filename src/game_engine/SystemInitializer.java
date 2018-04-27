package game_engine;

import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import voogasalad.util.reflection.Reflection;
import voogasalad.util.reflection.ReflectionException;

public class SystemInitializer {

	public List<GameSystem> init(Engine engine) {
		Reflections reflections = new Reflections("game_engine", new SubTypesScanner(true));
		Set<Class<? extends GameSystem>> allClasses = reflections.getSubTypesOf(GameSystem.class);

		List<GameSystem> systems = new ArrayList<>();

		allClasses.stream().filter(clazz -> isConcrete(clazz)).forEach(clazz -> {
			try {
				systems.add(createSystem(clazz, engine));
			} catch (ReflectionException e) {
				// Do nothing: Continue without this system.
				// No use in adding a NullSystem or anything like that since the user doesn't see it.
			}
		});

		return systems;
	}

	private GameSystem createSystem(Class<? extends GameSystem> clazz, Engine engine) {
		Parameter[] params = clazz.getDeclaredConstructors()[0].getParameters();
		GameSystem system;
		if (params.length > 0) {
			system = (GameSystem) Reflection.createInstance(clazz.getName(), engine);
		} else {
			system = (GameSystem) Reflection.createInstance(clazz.getName());
		}
		return system;
	}

	private boolean isConcrete(Class<? extends GameSystem> clazz) {
		return !Modifier.isAbstract(clazz.getModifiers());
	}
}
