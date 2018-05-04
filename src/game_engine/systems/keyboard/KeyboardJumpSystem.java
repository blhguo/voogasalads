package game_engine.systems.keyboard;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.NumberOfJumpsAllowedComponent;
import game_engine.components.PrimeComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.components.keyboard.UpKeyboardComponent;
import game_engine.components.physics.DefaultYVelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.level.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Jeremy Chen, Kevin Deng, Ben Hubsch, Andy Nguyen
 * 
 *         The purpose of this system class is to act on all entities that contain the YPhysics,
 *         KeyboardJumpInput, and Jump components. This system loops over all keypresses taken from
 *         the input queue in engine and maps these keypresses to potential jumping actions within
 *         these respective Entities.
 *
 */
public class KeyboardJumpSystem implements GameSystem {

	private static final Class<? extends Component<Double>> DEFAULT_Y_VEL = DefaultYVelComponent.class;
	private static final Class<? extends Component<Double>> Y_VEL = YVelComponent.class;
	private static final Class<? extends Component<KeyCode>> KEYBOARD_JUMP_INPUT = KeyboardJumpInputComponent.class;
	private static final Class<? extends Component<Double>> NUM_JUMPS = NumberOfJumpsAllowedComponent.class;
	private static final Class<? extends Component<UUID>> PRIME = PrimeComponent.class;
	private static final String KEY_PRESSED = "KEY_PRESSED";

	private Engine myEngine;

	/**
	 * Creates a new instance of the KeyboardJumpSystem
	 * 
	 * @param engine
	 */
	public KeyboardJumpSystem(Engine engine) {
		myEngine = engine;
	}

	/**
	 * This method loops over all entities that contain the correct components necessary for jumping
	 * through keyboard input. It then acts on these entities by changing the y velocity of the YPhysics
	 * component of these entities whenever the correct jumping keypress is read in from the input queue
	 * in Engine.
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(DEFAULT_Y_VEL, Y_VEL, KEYBOARD_JUMP_INPUT, NUM_JUMPS, PRIME);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<Double> defaultYVel = entity.getComponent(DEFAULT_Y_VEL);
			Component<Double> yVelocity = entity.getComponent(Y_VEL);
			Component<KeyCode> jumpInput = entity.getComponent(KEYBOARD_JUMP_INPUT);
			Component<Double> numJumps = entity.getComponent(NUM_JUMPS);
			for (KeyEvent input : myEngine.getKeyInputs(jumpInput.getValue())) {
				if (!checkEquals(input, KEY_PRESSED)) {
					continue;
				}
				
				double currNumberJumps = numJumps.getValue();
				if (currNumberJumps != 0) {
					double changedVel = defaultYVel.getValue();
					yVelocity.setValue(changedVel);
					numJumps.setValue(currNumberJumps - 1);
				}
			}
		}
	}
	
	private boolean checkEquals(KeyEvent event, String eventType) {
		return event.getEventType().getName().equals(eventType);
	}
	
}
