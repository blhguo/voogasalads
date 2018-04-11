package game_player;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.Level;
import game_engine.components.JumpComponent;
import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.components.keyboard.KeyboardMovementInputComponent;
import game_engine.components.physics.XPhysicsComponent;
import game_engine.components.physics.YPhysicsComponent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * 
 * @author Dana Park
 *
 */
public class PlayerView {

	private Timeline animation;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final double DOUBLE_RATE = 2.0;
	private static final double HALF_RATE = 0.5;
	private static final double SCENE_SIZE = 500;
	private PulldownFactory pullDownFactory;
	private Engine myEngine;

	private Map<ImageView, Entity> spriteMap;
	private Stage myStage;
	private Group root;
	private Camera cam;
	private ViewManager viewManager;
	private SubScene subScene;
	public PlayerView(PulldownFactory pdf, Engine engine, ViewManager view) {
		pullDownFactory = pdf;
		myEngine = engine;
		viewManager=view;
		instantiate(null);
		animationFrame();
	}

	public void instantiate(List<Level> levels) {
		Scene scene = viewManager.getScene();
		subScene= viewManager.getSubScene();
		root = viewManager.getSubRoot();
		scene.setOnKeyPressed(e -> {
			
		myEngine.receiveInput(e);
		});
		scene.setOnKeyReleased(e -> myEngine.receiveInput(e));
		
		subScene.setOnKeyPressed(e -> {
			
			myEngine.receiveInput(e);
			System.out.println("subscene");});
			subScene.setOnKeyReleased(e -> myEngine.receiveInput(e));
		
		cam = new ParallelCamera();
		subScene.setCamera(cam);
		//		for(Entity e: levels.get(0).getEntities()){
		//			myEngine.addEntity(e);
		//		}
		
		testingStuffInitializeEntity();

		spriteMap = new HashMap<ImageView, Entity>();
		List<Entity> spriteEntities = myEngine.getEntitiesContaining(Arrays.asList(SpriteComponent.class, PositionComponent.class));
		for(Entity e: spriteEntities){
			String imageName = ((SpriteComponent) e.getComponent(SpriteComponent.class)).getFileName();
			Image image = new Image(imageName);
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(40);
			imageView.setFitWidth(40);
			spriteMap.put(imageView, e);
			root.getChildren().add(imageView);
		}
		Image image1 = new Image("turtle.GIF");
		ImageView imageView2 = new ImageView(image1);
		root.getChildren().add(imageView2);
	}
	
	public void setViewManager(ViewManager vm) {
		 viewManager = vm;
	 }

	private void animationFrame() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step(double delay) {
		myEngine.update(delay);
		render();
		handleUI();		
	}

	private void render(){
		for(ImageView imageView : spriteMap.keySet()) {
			Entity entity = spriteMap.get(imageView);
			PositionComponent position = (PositionComponent) entity.getComponent(PositionComponent.class);
			SpriteComponent sprite = (SpriteComponent) entity.getComponent(SpriteComponent.class);
			imageView.setX(position.getX() - sprite.getWidth()/2);
			imageView.setY(position.getY() - sprite.getHeight()/2);
			imageView.setRotate(sprite.getAngle()); //TODO: figure out logistics of rotate
		}
		List<Entity> entity = myEngine.getEntitiesContaining(Arrays.asList(KeyboardMovementInputComponent.class));
		PositionComponent position = (PositionComponent) entity.get(0).getComponent(PositionComponent.class);
		cam.setLayoutX(position.getX()-SCENE_SIZE/2);
		cam.setLayoutY(position.getY()-SCENE_SIZE/2);
	}

	private void handleUI() {
		String selectedAction = pullDownFactory.SpeedBox().getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Speed Up")) {
			animation.setRate(animation.getRate() * DOUBLE_RATE);
		}
		if (selectedAction.equals("Slow Down")) {
			animation.setRate(animation.getRate() * HALF_RATE);
		}
		if (selectedAction.equals("Pause")) {
			animation.stop();
		}
		if (selectedAction.equals("Play")) {
			animation.play();
		}
	}

	private void testingStuffInitializeEntity() {
		//Create one entity
		Entity myEntity = new Entity();
		myEngine.addEntity(myEntity);

		//Movement Input Componenet		
		List<String> keyboardMovementInputArgs = new ArrayList<>();
		keyboardMovementInputArgs.add(KeyCode.LEFT.toString());
		keyboardMovementInputArgs.add(KeyCode.RIGHT.toString());
		KeyboardMovementInputComponent keyboardInputComponent = new KeyboardMovementInputComponent(keyboardMovementInputArgs);
		myEntity.addComponent(keyboardInputComponent);

		List<String> spriteArgs = new ArrayList<>();
		spriteArgs.add("mario.GIF");
		spriteArgs.add("true"); //is visible
		spriteArgs.add("40");
		spriteArgs.add("40");
		spriteArgs.add("0"); //angle
		SpriteComponent spriteComponent = new SpriteComponent(spriteArgs); //Create sprite
		myEntity.addComponent(spriteComponent); //Add sprite component to entity

		//Position Component
		List<String> positionArgs = new ArrayList<>();
		positionArgs.add("100"); //X position
		positionArgs.add("100"); //Y 
		positionArgs.add("100"); //angle
		PositionComponent positionComponent = new PositionComponent(positionArgs);
		myEntity.addComponent(positionComponent);

		//XPhysics
		List<String> xPhysicsArgs = new ArrayList<>();
		xPhysicsArgs.add("400"); //X velocity aka maxX velocity aka dx (the distance it moves each step)
		xPhysicsArgs.add("0"); //acceleration
		XPhysicsComponent xPhysicsComponent = new XPhysicsComponent(xPhysicsArgs);
		xPhysicsComponent.setCurrVel(10);
		myEntity.addComponent(xPhysicsComponent);

		//YPhysics Component
		List<String> yPhysicsArgs = new ArrayList<>();
		yPhysicsArgs.add("500"); //X velocity aka maxX velocity aka dx (the distance it moves each step)
		yPhysicsArgs.add("-1000"); //acceleration
		YPhysicsComponent yPhysicsComponent = new YPhysicsComponent(yPhysicsArgs);
		myEntity.addComponent(yPhysicsComponent);

		//Jump Component
		List<String> jumpArgs = new ArrayList<String>();
		//jumpArgs.add("true");
		jumpArgs.add("-1"); //number of jumps
		JumpComponent jumpComponent = new JumpComponent(jumpArgs);
		myEntity.addComponent(jumpComponent);

		//Jump Input Component
		ArrayList<String> jumpInputArgs = new ArrayList<String>();
		jumpInputArgs.add(KeyCode.A.toString()); // Press UP for jump
		KeyboardJumpInputComponent keyboardJumpInputComponent = new KeyboardJumpInputComponent(jumpInputArgs);
		myEntity.addComponent(keyboardJumpInputComponent);
	}

}
