package game_engine;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameSystemInitializer {
	private static final String GAME_SYSTEM_NAMES = "GameSystems";
	private static final String GAME_SYSTEM_ARGS = "GameSystemsArguments";
	
	private ResourceBundle mySystemClassNames;
	private ResourceBundle mySystemArgs;
	
	public GameSystemInitializer(){
		mySystemClassNames = ResourceBundle.getBundle(GAME_SYSTEM_NAMES);
		mySystemArgs = ResourceBundle.getBundle(GAME_SYSTEM_ARGS);
	}
	
	public List<GameSystem> initializeSystems(Engine arg){
		List<GameSystem> initialSystems = new ArrayList<>();
		for (String systemName : mySystemClassNames.keySet()) {
			System.out.println(systemName);
			String className = mySystemClassNames.getString(systemName);
			System.out.println(className);
			try {
				Class<?> clazz = Class.forName(className);
				System.out.println(clazz);
				Constructor<?> ctor = clazz.getDeclaredConstructor(String.class);
				System.out.println(ctor);
				if(mySystemArgs.getString(systemName).equals("engine")){
					initialSystems.add((GameSystem) ctor.newInstance(arg));
				}else{
					initialSystems.add((GameSystem) ctor.newInstance());
				}
			} catch (Exception e) {
				throw new ComponentNotFoundException("Component not found.");
			}
		}
		return initialSystems;
	}
}
