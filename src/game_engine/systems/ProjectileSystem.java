package game_engine.systems;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.projectile.ProjectileCollidableComponent;
import game_engine.components.projectile.ProjectileDamageComponent;
import game_engine.components.projectile.ProjectileFilenameComponent;
import game_engine.components.projectile.ProjectileHeightComponent;
import game_engine.components.projectile.ProjectileHitboxHeightComponent;
import game_engine.components.projectile.ProjectileHitboxWidthComponent;
import game_engine.components.projectile.ProjectileKeyboardInputComponent;
import game_engine.components.projectile.ProjectileWidthComponent;
import game_engine.components.projectile.ProjectileXVelComponent;
import game_engine.components.projectile.ProjectileYVelComponent;
import game_engine.level.Level;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;

public class ProjectileSystem extends GameSystem {

	private static final Class<? extends Component<Double>> PROJ_YVEL = ProjectileYVelComponent.class;
	private static final Class<? extends Component<Double>> PROJ_XVEL = ProjectileXVelComponent.class;
	private static final Class<? extends Component<Double>> PROJ_WIDTH = ProjectileWidthComponent.class;
	private static final Class<? extends Component<Double>> PROJ_HEIGHT = ProjectileHeightComponent.class;
	private static final Class<? extends Component<Double>> PROJ_HITBOX_HEIGHT = ProjectileHitboxHeightComponent.class;
	private static final Class<? extends Component<Double>> PROJ_HITBOX_WIDTH = ProjectileHitboxWidthComponent.class;
	private static final Class<? extends Component<Double>> PROJ_DAMAGE = ProjectileDamageComponent.class;
	private static final Class<? extends Component<Boolean>> PROJ_COLLIDABLE = ProjectileCollidableComponent.class;
	private static final Class<? extends Component<String>> PROJ_FILENAME = ProjectileFilenameComponent.class;
	private static final Class<? extends Component<KeyCode>> PROJ_INPUT = ProjectileKeyboardInputComponent.class;
	private static final Class<? extends Component<Double>> ENTITY_XPOS = XPosComponent.class;
	private static final Class<? extends Component<Double>> ENTITY_YPOS = YPosComponent.class;
	
	private Engine myEngine;
	private static final String KEY_PRESSED = "KEY_PRESSED";
	
	public ProjectileSystem(Engine engine) {
		myEngine = engine;
	}
	
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(PROJ_YVEL, PROJ_XVEL, PROJ_WIDTH, PROJ_HEIGHT,
				PROJ_HITBOX_HEIGHT, PROJ_HITBOX_WIDTH, PROJ_DAMAGE, PROJ_COLLIDABLE, PROJ_FILENAME, PROJ_INPUT, 
				ENTITY_XPOS, ENTITY_YPOS);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<KeyCode> keyInput = entity.getComponent(PROJ_INPUT);
			for (InputEvent input : myEngine.getInput(keyInput)) {
				if (input.getEventType().getName().equals(KEY_PRESSED)) {
					createProjectile(entity, args);
				}
			}
		}
	}
	
	private void createProjectile(Entity entity, List<Class<? extends Component<?>>> args){
		Entity projectile = new Entity();
		for (Class<? extends Component<?>> clazz : args) {
			Constructor<?> ctor;
			try {
				ctor = clazz.getDeclaredConstructor(new Class [] { String.class } );
				projectile.addComponent((Component<?>) ctor.newInstance(entity.getComponent(clazz)));
			} catch (Exception e) {
				// Do nothing; projectile will simply not get added to screen.
			}
		}
	}

}
