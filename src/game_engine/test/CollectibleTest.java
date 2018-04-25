package game_engine.test;

import java.util.HashMap;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
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
import game_engine.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CollectibleTest extends Application {

	private Entity mainCharacter; //smol rect
	private Entity coin; //BIG rect

	Map<Entity, Rectangle> spritesMap = new HashMap<Entity, Rectangle>();

	private Engine engine;

	private Rectangle mainCharacterRect;
	private Rectangle coinRect;

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
		printOutMainCharacterScore();
	}

	private void printOutMainCharacterScore(){
		System.out.println("Score: " + mainCharacter.getComponent(ScoreComponent.class).getValue());
	}

	private void setup(){
		root = new Group();
		myScene = new Scene(root, WIDTH, HEIGHT, BACKGROUND);
		myScene.setOnKeyPressed(b -> engine.receiveInput(b));
		myScene.setOnKeyReleased(b -> engine.receiveInput(b));

		buildMainCharacterEntity();
		buildCoinEntity();
		buildEngine();
		initRects();
	}

	private void updateAllRects() {
		root.getChildren().clear();
		for (Entity entity : engine.getLevel().getEntities()) {
			if (!spritesMap.isEmpty()){
				Rectangle r = spritesMap.get(entity);
				double xPos = entity.getComponent(XPosComponent.class).getValue();
				double yPos = entity.getComponent(YPosComponent.class).getValue();
				double width = entity.getComponent(HitboxWidthComponent.class).getValue();
				double height = entity.getComponent(HitboxHeightComponent.class).getValue();
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
		mainCharacter.addComponent(new LeftKeyboardComponent(KeyCode.LEFT.toString()));
		mainCharacter.addComponent(new RightKeyboardComponent(KeyCode.RIGHT.toString()));
		mainCharacter.addComponent(new UpKeyboardComponent(KeyCode.UP.toString()));
		mainCharacter.addComponent(new DownKeyboardComponent(KeyCode.DOWN.toString()));
		mainCharacter.addComponent(new XVelComponent("0"));
		mainCharacter.addComponent(new YVelComponent("0"));

		//extra components needed for velocity system
		mainCharacter.addComponent(new XAccelComponent("0"));
		mainCharacter.addComponent(new YAccelComponent("0"));

		//extra components needed for position system
		mainCharacter.addComponent(new XPosComponent("500"));
		mainCharacter.addComponent(new YPosComponent("350"));

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
		coin.addComponent(new XPosComponent("800"));
		coin.addComponent(new YPosComponent("300"));
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
		double x1 = mainCharacter.getComponent(XPosComponent.class).getValue();
		double y1 = mainCharacter.getComponent(YPosComponent.class).getValue();

		double x3 = coin.getComponent(XPosComponent.class).getValue();
		double y3 = coin.getComponent(YPosComponent.class).getValue();

		double hh1 = mainCharacter.getComponent(HitboxHeightComponent.class).getValue();
		double hw1 = mainCharacter.getComponent(HitboxWidthComponent.class).getValue();

		double hh3 = coin.getComponent(HitboxHeightComponent.class).getValue();
		double hw3 = coin.getComponent(HitboxWidthComponent.class).getValue();

		mainCharacterRect = new Rectangle(x1 - hw1/2, y1 - hh1/2, hw1, hh1);
		coinRect = new Rectangle(x3 - hw3/2, y3 - hh3/2, hw3, hh3);

		spritesMap.put(mainCharacter, mainCharacterRect);
		spritesMap.put(coin, coinRect);

		root.getChildren().add(mainCharacterRect);
		root.getChildren().add(coinRect);
	}


	/**
	 * Starts the program
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
