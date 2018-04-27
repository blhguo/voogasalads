package game_engine.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameData.ManipData;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.DamageComponent;
import game_engine.components.HealthComponent;
import game_engine.components.collision.CollidableComponent;
import game_engine.components.collision.PassableComponent;
import game_engine.components.collision.hitbox.HitboxHeightComponent;
import game_engine.components.collision.hitbox.HitboxWidthComponent;
import game_engine.components.collision.hitbox.HitboxXOffsetComponent;
import game_engine.components.collision.hitbox.HitboxYOffsetComponent;
import game_engine.components.keyboard.DownKeyboardComponent;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.keyboard.RightKeyboardComponent;
import game_engine.components.keyboard.UpKeyboardComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.DefaultYVelComponent;
import game_engine.components.physics.XAccelComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YAccelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.components.position.AngleComponent;
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
import game_engine.level.Level;
import game_engine.systems.DespawnSystem;
import game_engine.systems.HealthSystem;
import game_engine.systems.PositionSystem;
import game_engine.systems.ProjectileDespawnSystem;
import game_engine.systems.ProjectileSpawnSystem;
import game_engine.systems.VelocitySystem;
import game_engine.systems.collision.CollisionBroadSystem;
import game_engine.systems.collision.ImpassableResponseSystem;
import game_engine.systems.keyboard.DownKeyboardMovementSystem;
import game_engine.systems.keyboard.KeyboardJumpSystem;
import game_engine.systems.keyboard.LeftKeyboardMovementSystem;
import game_engine.systems.keyboard.RightKeyboardMovementSystem;
import game_engine.systems.keyboard.UpKeyboardMovementSystem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class saveDataTest extends Application {
	private Engine engine;

	private Rectangle r1;

	private Group root;
	private Scene myScene;

	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private Entity e1;
	private Entity e2;
	private Entity e3;

	public void initializeEverything() {
		engine = new Engine();
	}

	public void makeEntities() {
		e1 = new Entity();
		e2 = new Entity();
		e3 = new Entity();

		LeftKeyboardComponent keyLeftComp = new LeftKeyboardComponent(KeyCode.LEFT.toString());
		RightKeyboardComponent keyRightComp = new RightKeyboardComponent(KeyCode.RIGHT.toString());
		UpKeyboardComponent keyUpComp = new UpKeyboardComponent(KeyCode.UP.toString());
		DownKeyboardComponent keyDownComp = new DownKeyboardComponent(KeyCode.DOWN.toString());
		e1.addComponent(keyLeftComp);
		e1.addComponent(keyRightComp);
		e1.addComponent(keyUpComp);
		e1.addComponent(keyDownComp);

		e1.addComponent(new YAccelComponent("20"));
		e1.addComponent(new DefaultYVelComponent("20"));
		e1.addComponent(new YVelComponent("0"));

		e1.addComponent(new XVelComponent("0"));
		e1.addComponent(new DefaultXVelComponent("400"));
		e1.addComponent(new XAccelComponent("0"));

		e1.addComponent(new XPosComponent("0"));
		e1.addComponent(new YPosComponent("0"));
		e1.addComponent(new AngleComponent("0"));

		e2.addComponent(new XPosComponent("100"));
		e2.addComponent(new YPosComponent("100"));
		e2.addComponent(new AngleComponent("0"));

		e3.addComponent(new XPosComponent("800"));
		e3.addComponent(new YPosComponent("300"));
		e3.addComponent(new AngleComponent("0"));

		e1.addComponent(new HitboxHeightComponent("100.0"));
		e1.addComponent(new HitboxWidthComponent("100.0"));
		e1.addComponent(new HitboxXOffsetComponent("0.0"));
		e1.addComponent(new HitboxYOffsetComponent("0.0"));

		e2.addComponent(new HitboxHeightComponent("20.0"));
		e2.addComponent(new HitboxWidthComponent("20.0"));
		e2.addComponent(new HitboxXOffsetComponent("0.0"));
		e2.addComponent(new HitboxYOffsetComponent("0.0"));

		e3.addComponent(new HitboxHeightComponent("500.0"));
		e3.addComponent(new HitboxWidthComponent("100.0"));
		e3.addComponent(new HitboxXOffsetComponent("0.0"));
		e3.addComponent(new HitboxYOffsetComponent("0.0"));

		e1.addComponent(new CollidableComponent("true"));
		// e1.addComponent(new PassableComponent("true"));

		e2.addComponent(new CollidableComponent("true"));
		e2.addComponent(new PassableComponent("true"));

		e3.addComponent(new CollidableComponent("true"));
		// e3.addComponent(new PassableComponent("true"));

		// Add Health Component
		e1.addComponent(new HealthComponent("1000")); // 100 health points for e1
		e3.addComponent(new HealthComponent("2000"));
		e3.addComponent(new DamageComponent("100")); // e3 does 100 damage

		// Add Project Components to Entity e1
		e1.addComponent(new ProjectileWidthComponent("30.0"));
		e1.addComponent(new ProjectileHeightComponent("20.0"));
		e1.addComponent(new ProjectileHitboxWidthComponent("20.0"));
		e1.addComponent(new ProjectileHitboxHeightComponent("20.0"));
		e1.addComponent(new ProjectileHitboxXOffsetComponent("0.0"));
		e1.addComponent(new ProjectileHitboxYOffsetComponent("0.0"));
		e1.addComponent(new ProjectileCollidableComponent("true"));
		e1.addComponent(new ProjectileDamageComponent("2"));
		e1.addComponent(new ProjectileKeyboardInputComponent(KeyCode.SPACE.toString()));
		e1.addComponent(new ProjectileXVelComponent("200"));
		e1.addComponent(new ProjectileYVelComponent("0"));
		e1.addComponent(new ProjectileFilenameComponent("Mario.GIF"));

		Level asdf = engine.createLevel();
		asdf.addEntity(e2);
		asdf.addEntity(e1);
		asdf.addEntity(e3);

		List<Level> levels = new ArrayList<Level>();
		levels.add(asdf);
	}

	public void setUpRect() {
		r1 = new Rectangle();
		double x = e1.getComponent(XPosComponent.class).getValue();
		double y = e1.getComponent(YPosComponent.class).getValue();
		r1.setWidth(50);
		r1.setHeight(50);
		r1.setX(x);
		r1.setY(y);
		r1.setFill(Color.BLUE);
	}

	public void update(double elapsed) {
		engine.update(elapsed);
		Entity e1 = engine.getLevel().getEntities().get(1);
		double x = e1.getComponent(XPosComponent.class).getValue();
		double y = e1.getComponent(YPosComponent.class).getValue();
		double theta = e1.getComponent(AngleComponent.class).getValue();
		System.out.println("x: " + x);
		System.out.println(" y: " + y);
		r1.setX(x);
		r1.setY(y);
	}

	public void saveIntoData() {
		ManipData data = new ManipData();
		Map<String, String> map = new HashMap<>();
		map.put("dog", "cat");
		map.put("potato", "fruit");
		data.saveData(engine, "Mario", map);
	}

	public Engine loadFromData() {
		ManipData data = new ManipData();
		System.out.println("hi");
		System.out.println(data.loadData("games/Mario/Mario.xml", "gameName"));
		return data.loadData("games/Mario/Mario.xml", "gameName");
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.initializeEverything();
		this.makeEntities();
		this.saveIntoData();
		this.setUpRect();
		engine = this.loadFromData();

		System.out.println("hiasdfasdf");
		root = new Group();
		myScene = new Scene(root, 400, 400, Color.rgb(255, 255, 255));
		myScene.setOnKeyPressed(b -> engine.receiveInput(b));
		myScene.setOnKeyReleased(b -> engine.receiveInput(b));

		root.getChildren().add(r1);
		stage.setScene(myScene);
		stage.setTitle("mario");
		stage.show();

		// attach "game loop" to timeline to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> update(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();

	}
}
