package test;

import java.util.Set;

import org.reflections.Reflections;

public class Tester {
	public static void main(String args[]) {
		 Reflections reflections = new Reflections("voogasalad_callussalad.src.game_engine");

		 Set<Class<? extends Object>> allClasses = 
		     reflections.getSubTypesOf(Object.class);
		 System.out.println(allClasses);
	}
}

