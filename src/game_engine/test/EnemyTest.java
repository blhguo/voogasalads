package game_engine.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import gameData.ManipData;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.PrimeComponent;
import game_engine.components.collect.CollectibleComponent;
import game_engine.components.collect.CollectorComponent;
import game_engine.components.collect.ScoreComponent;
import game_engine.components.collision.CollidableComponent;
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
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.SpritePolarityComponent;
import game_engine.components.sprite.VisibilityComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.components.sprite.ZHeightComponent;
import game_engine.event.Event;
import game_engine.event.actions.micro.DataChangeAction;
import game_engine.event.conditions.MouseInputCondition;
import game_engine.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EnemyTest extends Application {

	private Entity mainCharacter; //smol rect
	private Entity coin; //BIG rect

	Map<Entity, ImageView> spritesMap = new HashMap<>();

	private Engine engine;

	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;    

	private static final int WIDTH = 1600;
	private static final int HEIGHT= 800;
	private static final Paint BACKGROUND = Color.rgb(36, 36, 36);

	private Group root;
	private Scene myScene;

	@Override
	public void start(Stage stage) throws Exception {
		setup();
		stage.setScene(myScene);
		stage.show();

		// attach "game loop" to timeline to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	/**
	 * @param elapsedTime
	 */
	private void step(double elapsedTime) {
		engine.update(elapsedTime);
		updateAllRects();
	}

	private void setup(){
		root = new Group();
		myScene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);

		buildMainCharacterEntity();
		buildCoinEntity();
		buildEngine();
		initRects();
		addMouseClickEvents();
		
		ManipData data = new ManipData();
		Map<String, String> map = new HashMap<>();
		map.put("dog", "cat");
		map.put("potato", "fruit");
		data.saveData(engine, "Mario","Mario", true);
	}

	private void updateAllRects() {
		root.getChildren().clear();
		for (Entity entity : engine.getLevel().getEntities()) {
			if (!spritesMap.isEmpty()){
				ImageView r = spritesMap.get(entity);
				double xPos = entity.getComponent(XPosComponent.class).getValue();
				double yPos = entity.getComponent(YPosComponent.class).getValue();
				double width = entity.getComponent(WidthComponent.class).getValue();
				double height = entity.getComponent(HeightComponent.class).getValue();
				r.setX(xPos - width/2);
				r.setY(yPos - height/2);
				root.getChildren().add(r);
			}
		}
	}

	private void buildMainCharacterEntity(){
		mainCharacter = new Entity();
		
		//components needed for each of the 4 keyboard movement systems
		mainCharacter.addComponent(new DefaultXVelComponent("200"));
		mainCharacter.addComponent(new DefaultYVelComponent("200"));
		mainCharacter.addComponent(new LeftKeyboardComponent(KeyCode.A.toString()));
		mainCharacter.addComponent(new RightKeyboardComponent(KeyCode.D.toString()));
		mainCharacter.addComponent(new UpKeyboardComponent(KeyCode.F.toString()));
		mainCharacter.addComponent(new DownKeyboardComponent(KeyCode.S.toString()));
		mainCharacter.addComponent(new XVelComponent("0"));
		mainCharacter.addComponent(new YVelComponent("0"));
		mainCharacter.addComponent(new PrimeComponent());
		mainCharacter.addComponent(new VisibilityComponent("true"));

		//extra components needed for velocity system
		mainCharacter.addComponent(new XAccelComponent("0"));
		mainCharacter.addComponent(new YAccelComponent("0"));

		//extra components needed for position system
		mainCharacter.addComponent(new XPosComponent("500"));
		mainCharacter.addComponent(new YPosComponent("300"));

		//extra components needed for collision detection
		mainCharacter.addComponent(new HitboxHeightComponent("100.0"));
		mainCharacter.addComponent(new HitboxWidthComponent("100.0"));
		mainCharacter.addComponent(new HitboxXOffsetComponent("0.0"));
		mainCharacter.addComponent(new HitboxYOffsetComponent("0.0"));
		mainCharacter.addComponent(new CollidableComponent("true"));

		//components needed for collectible system
		mainCharacter.addComponent(new CollectorComponent());
		mainCharacter.addComponent(new ScoreComponent("0"));
	}

	private void buildCoinEntity(){
		coin = new Entity();
		
		//component needed for collectible system
		coin.addComponent(new CollectibleComponent("50"));

		//component needed for collision detection system and position
		coin.addComponent(new CollidableComponent("true"));
		coin.addComponent(new HitboxHeightComponent("500.0"));
		coin.addComponent(new HitboxWidthComponent("100.0"));
		coin.addComponent(new HitboxXOffsetComponent("0.0"));
		coin.addComponent(new HitboxYOffsetComponent("0.0"));
		coin.addComponent(new XPosComponent("700"));
		coin.addComponent(new YPosComponent("300"));
	}
	
	private void addMouseClickEvents() {
		//Testing Mouse event on main character (Mario) - changes speed
		MouseInputCondition condition1 = new MouseInputCondition(engine, mainCharacter);
		DataChangeAction action1 = new DataChangeAction(mainCharacter, DefaultXVelComponent.class, "+", 100);
		Event event1 = new Event(Arrays.asList(action1), Arrays.asList(condition1));
		engine.getLevel().addEvent(event1);
	}

	private void buildEngine(){
		System.out.println("engine");
		engine = new Engine();
		System.out.println("hi");
		Level level1 = engine.createLevel();
		level1.addEntity(mainCharacter);
		level1.addEntity(coin);
	}

	private void initRects(){
		// components needed for sprite component
		mainCharacter.addComponent(new FilenameComponent("dot.png"));
		mainCharacter.addComponent(new HeightComponent("20"));
		mainCharacter.addComponent(new WidthComponent("20"));
		mainCharacter.addComponent(new ZHeightComponent("2"));
		mainCharacter.addComponent(new SpritePolarityComponent("1"));
		mainCharacter.addComponent(new VisibilityComponent("true"));
		
		// component needed for sprite component
		coin.addComponent(new FilenameComponent("turtle.GIF"));
		coin.addComponent(new HeightComponent("50"));
		coin.addComponent(new WidthComponent("50"));
		coin.addComponent(new ZHeightComponent("1"));
		coin.addComponent(new VisibilityComponent("true"));
		
		ImageView mainView = new ImageView(mainCharacter.getComponent(FilenameComponent.class).getValue());
		ImageView coinView = new ImageView(coin.getComponent(FilenameComponent.class).getValue());
		
		Entity referencePoint = new Entity();
		referencePoint.addComponent(new FilenameComponent("clouds.png"));
		referencePoint.addComponent(new XPosComponent("500"));
		referencePoint.addComponent(new YPosComponent("300"));
		referencePoint.addComponent(new HeightComponent("500"));
		referencePoint.addComponent(new WidthComponent("500"));
		referencePoint.addComponent(new ZHeightComponent("0"));
		referencePoint.addComponent(new VisibilityComponent("true"));
		engine.getLevel().addEntity(referencePoint);
		ImageView referenceView = new ImageView(referencePoint.getComponent(FilenameComponent.class).getValue());

		spritesMap.put(mainCharacter, mainView);
		spritesMap.put(coin, coinView);
		spritesMap.put(referencePoint, referenceView);

		root.getChildren().add(mainView);
		root.getChildren().add(coinView);
	}


	/**
	 * Starts the program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
