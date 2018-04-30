package game_engine.systems;

import java.util.Random;

import game_engine.GameSystem;
import game_engine.level.Level;

public class RainSystem extends GameSystem {
	
	private static final double CREATE_PERCENTAGE = 0.05;
	
	private Random myRandom = new Random();
	
	@Override
	public void act(double elapsedTime, Level level) {
		if (addRain()) {
			
		}
	}
	
	private boolean addRain() {
		return myRandom.nextDouble() < CREATE_PERCENTAGE;
	}

}
