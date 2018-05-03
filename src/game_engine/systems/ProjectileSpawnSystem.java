package game_engine.systems;

import java.util.Arrays;
import java.util.List;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.GameSystem;
import game_engine.components.DamageComponent;
import game_engine.components.ProjectileComponent;
import game_engine.components.collision.CollidableComponent;
import game_engine.components.collision.hitbox.HitboxHeightComponent;
import game_engine.components.collision.hitbox.HitboxWidthComponent;
import game_engine.components.collision.hitbox.HitboxXOffsetComponent;
import game_engine.components.collision.hitbox.HitboxYOffsetComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.projectile.ProjectileCollidableComponent;
import game_engine.components.projectile.ProjectileDamageComponent;
import game_engine.components.projectile.ProjectileFilenameComponent;
import game_engine.components.projectile.ProjectileHeightComponent;
import game_engine.components.projectile.ProjectileHitboxHeightComponent;
import game_engine.components.projectile.ProjectileHitboxWidthComponent;
import game_engine.components.projectile.ProjectileHitboxXOffsetComponent;
import game_engine.components.projectile.ProjectileHitboxYOffsetComponent;
import game_engine.components.projectile.ProjectileKeyboardInputComponent;
import game_engine.components.projectile.ProjectileWidthComponent;
import game_engine.components.projectile.ProjectileXVelComponent;
import game_engine.components.projectile.ProjectileYVelComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.SpritePolarityComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.level.Level;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Andy Nguyen, Jeremy Chen, Ben Hubsch, Kevin Deng
 * The purpose of this system is to allow entities to shoot projectiles upon user input. The system spawns projectiles
 * from an entity, with the projectiles having the attributes defined within the Projectile...Components. 
 *
 */
public class ProjectileSpawnSystem implements GameSystem {
	private static final Class<? extends Component<Double>> PROJ_YVEL = ProjectileYVelComponent.class;
	private static final Class<? extends Component<Double>> PROJ_XVEL = ProjectileXVelComponent.class;
	private static final Class<? extends Component<Double>> PROJ_WIDTH = ProjectileWidthComponent.class;
	private static final Class<? extends Component<Double>> PROJ_HEIGHT = ProjectileHeightComponent.class;
	private static final Class<? extends Component<Double>> PROJ_HITBOX_HEIGHT = ProjectileHitboxHeightComponent.class;
	private static final Class<? extends Component<Double>> PROJ_HITBOX_WIDTH = ProjectileHitboxWidthComponent.class;
	private static final Class<? extends Component<Double>> PROJ_HITBOX_X_OFFSET = ProjectileHitboxXOffsetComponent.class;
	private static final Class<? extends Component<Double>> PROJ_HITBOX_Y_OFFSET = ProjectileHitboxYOffsetComponent.class;
	private static final Class<? extends Component<Double>> PROJ_DAMAGE = ProjectileDamageComponent.class;
	private static final Class<? extends Component<Boolean>> PROJ_COLLIDABLE = ProjectileCollidableComponent.class;
	private static final Class<? extends Component<String>> PROJ_FILENAME = ProjectileFilenameComponent.class;
	private static final Class<? extends Component<KeyCode>> PROJ_INPUT = ProjectileKeyboardInputComponent.class;
	private static final Class<? extends Component<Double>> ENTITY_XPOS = XPosComponent.class;
	private static final Class<? extends Component<Double>> ENTITY_YPOS = YPosComponent.class;
	private static final String KEY_PRESSED = "KEY_PRESSED";
	
	private Engine myEngine;
	
	/**
	 * instantiates a new ProjectileSpawnSystem with the given reference to Engine
	 * @param engine
	 */
	public ProjectileSpawnSystem(Engine engine) {
		myEngine = engine;
	}
	
	/**
	 * Listens for specific shooting input from the engine and then spawns projectile Entities upon receiving the correct input
	 */
	@Override
	public void act(double elapsedTime, Level level) {
		List<Class<? extends Component<?>>> args = Arrays.asList(PROJ_YVEL, PROJ_XVEL, PROJ_WIDTH, PROJ_HEIGHT,
				PROJ_HITBOX_HEIGHT, PROJ_HITBOX_WIDTH, PROJ_DAMAGE, PROJ_COLLIDABLE, PROJ_FILENAME, PROJ_INPUT, 
				ENTITY_XPOS, ENTITY_YPOS, PROJ_HITBOX_X_OFFSET, PROJ_HITBOX_Y_OFFSET);
		for (Entity entity : level.getEntitiesContaining(args)) {
			Component<KeyCode> keyInput = entity.getComponent(PROJ_INPUT);
			for (KeyEvent input : myEngine.getKeyInputs(keyInput.getValue())) {
				if (input.getEventType().getName().equals(KEY_PRESSED)) {
					Entity proj = createProjectile(entity);
					level.addEntity(proj);
				}
			}
		}
	}
	
	/**
	 * creates a Projectile entity from the given values described withhin the Projectile...Components
	 * @param entity
	 * @return
	 */
	private Entity createProjectile(Entity entity) {
		Entity projectile = new Entity();
		projectile.addComponent(new ProjectileComponent());
		projectile.addComponent(new YVelComponent(entity.getComponent(PROJ_YVEL).getValue().toString()));
		
		Component<Double> polarity = entity.getComponent(SpritePolarityComponent.class);
		Double projectileXVel = entity.getComponent(PROJ_XVEL).getValue();
		
		double spawnXOffset = 0;
		Component<Double> entityHitboxWidth = entity.getComponent(HitboxWidthComponent.class);
		Component<Double> entityHitboxXOffset = entity.getComponent(HitboxXOffsetComponent.class);
		double projHitboxWidth = entity.getComponent(PROJ_HITBOX_WIDTH).getValue();
		
		if(entityHitboxWidth != null && entityHitboxXOffset != null) {
			spawnXOffset = (entityHitboxWidth.getValue() + entityHitboxXOffset.getValue() + projHitboxWidth)/2;
		}
		if(polarity!=null) {
			double dir = Math.signum(polarity.getValue());
			projectileXVel = projectileXVel * dir;
			spawnXOffset *= dir;
		}
		projectile.addComponent(new XVelComponent(projectileXVel.toString()));
		projectile.addComponent(new WidthComponent(entity.getComponent(PROJ_WIDTH).getValue().toString()));
		projectile.addComponent(new HeightComponent(entity.getComponent(PROJ_HEIGHT).getValue().toString()));
		projectile.addComponent(new HitboxHeightComponent(entity.getComponent(PROJ_HITBOX_HEIGHT).getValue().toString()));
		projectile.addComponent(new HitboxWidthComponent(entity.getComponent(PROJ_HITBOX_WIDTH).getValue().toString()));
		projectile.addComponent(new DamageComponent(entity.getComponent(PROJ_DAMAGE).getValue().toString()));
		projectile.addComponent(new CollidableComponent(entity.getComponent(PROJ_COLLIDABLE).getValue().toString()));
		projectile.addComponent(new FilenameComponent(entity.getComponent(PROJ_FILENAME).getValue().toString()));
		projectile.addComponent(new HitboxXOffsetComponent(entity.getComponent(PROJ_HITBOX_X_OFFSET).getValue().toString()));
		projectile.addComponent(new HitboxYOffsetComponent(entity.getComponent(PROJ_HITBOX_Y_OFFSET).getValue().toString()));
		
		
		projectile.addComponent(new YPosComponent(entity.getComponent(ENTITY_YPOS).getValue().toString()));
		projectile.addComponent(new XPosComponent(Double.toString(entity.getComponent(ENTITY_XPOS).getValue()+spawnXOffset)));
		

		return projectile;
	}

}
