package game_engine;

import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class SystemInitializer {
	private static final String SYSTEM_NAMES = "SystemNames";
	private static final String SYSTEM_ARGS = "SystemArgs";
	
	private ResourceBundle mySystemNames;
	private ResourceBundle mySystemArgs;
	
	public SystemInitializer(){
		mySystemNames = ResourceBundle.getBundle(SYSTEM_NAMES);
		mySystemArgs = ResourceBundle.getBundle(SYSTEM_ARGS);
	}
	
	public List<GameSystem> init(Engine engine) {
		List<GameSystem> systems = new ArrayList<>();
		for(String name : mySystemNames.keySet()){
			systems.add(createSystem(name, engine));
		}
		return systems;
	}

	private GameSystem createSystem(String systemName, Engine engine) {
		GameSystem system;
		if (mySystemArgs.getString(systemName).equals("engine")) {
			system = (GameSystem) Reflection.createInstance(mySystemNames.getString(systemName), engine);
		} else {
			system = (GameSystem) Reflection.createInstance(mySystemNames.getString(systemName));
		}
		return system;
	}
}
