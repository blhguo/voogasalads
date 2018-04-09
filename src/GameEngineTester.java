import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.Vector;
import game_engine.components.KeyboardMovementInputComponent;
import game_engine.components.PhysicsComponent;
import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import game_engine.systems.KeyboardMovementSystem;
import game_engine.systems.MovementSystem;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameEngineTester extends Application{

	private Stage myStage;
	private Group myRoot;
	private Scene myScene;
	
	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private static final String TITLE = "Game Engine Test";
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private Engine myEngine;
	private MovementSystem movementSystem;
	private KeyboardMovementSystem keyboardSystem;
	private Entity myEntity;
	private ImageView myEntityImage;


	@Override
	public void start(Stage stage) throws Exception {
		
		myEngine = new Engine();
		myEntity = new Entity();
		myEngine.addEntity(myEntity);
		
		myStage = stage;
		myScene = setStage(WIDTH, HEIGHT, Color.AZURE); // include this in an initialize function
		myStage.setScene(myScene);
		myStage.setTitle(TITLE);
		myStage.show();
		
		// attach "game loop" to timeline to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();		
	}

	/**
	 * Method to create the scene used for the simulation
	 * 
	 * @param width
	 * @param height
	 * @param background
	 * @return
	 */
	private Scene setStage(int width, int height, Paint background) {
		myRoot = new Group();
		Scene scene = new Scene(myRoot, width, height, background);
		myRoot.getChildren().add(new Text(75, 200, "THIS IS A TEST"));
		
		scene.setOnKeyPressed(e -> myEngine.receiveInput(e));

		//TESTS BELOW
		initialSprite();
		initialMovement();


		return scene;
	}

	private void initialSprite() {
		//TEST SPRITES		
		Map<Entity, String> spriteMap = new HashMap<>(); //Simulate authoring env. map of Entity to Sprite filename

		List<String> spriteArgs = new ArrayList<>();
		spriteArgs.add("turtle.GIF");
		spriteArgs.add("true");
		spriteArgs.add("40");
		spriteArgs.add("40");
		SpriteComponent spriteComponent = new SpriteComponent(spriteArgs); //Create sprite
		myEntity.addComponent(spriteComponent); //Add sprite component to entity

		spriteMap.put(myEntity, spriteComponent.getFileName());

		//Use Imageview to display sprite
		myEntityImage = new ImageView();
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(spriteMap.get(myEntity)));
		myEntityImage.setImage(image);
		myEntityImage.setFitWidth(spriteComponent.getWidth());
		myEntityImage.setFitHeight(spriteComponent.getHeight());
		myRoot.getChildren().add(myEntityImage);	
	}

	private void initialMovement() {
		//Movement Input Componenet		
		List<String> keyboardMovementInputArgs = new ArrayList<>();
		keyboardMovementInputArgs.add(KeyCode.LEFT.toString());
		keyboardMovementInputArgs.add(KeyCode.RIGHT.toString());
		KeyboardMovementInputComponent keyboardInputComponent = new KeyboardMovementInputComponent(keyboardMovementInputArgs);
		myEntity.addComponent(keyboardInputComponent);
	
		
		//Physics Component
		List<String> physicsArgs = new ArrayList<>();
		physicsArgs.add("10"); //X velocity aka maxX velocity aka dx (the distance it moves each step)
		physicsArgs.add("0"); //Y velocity
		physicsArgs.add("0"); //acceleration
		PhysicsComponent physicsComponent = new PhysicsComponent(physicsArgs);
		physicsComponent.setCurrXVel(0);
		myEntity.addComponent(physicsComponent);
		
		//Position Component
		List<String> positionArgs = new ArrayList<>();
		positionArgs.add("100"); //X position
		positionArgs.add("100"); //Y 
		positionArgs.add("0"); //acceleration
		PositionComponent positionComponent = new PositionComponent(positionArgs);
		myEntity.addComponent(positionComponent);
			
		//Create Systems
		movementSystem = new MovementSystem(myEngine);
		keyboardSystem = new KeyboardMovementSystem(myEngine);
		
//	
//		PhysicsComponent physicsComponent = new PhysicsComponent(null);
//		myScene.setOnKeyPressed(e -> {
//			Vector direction = keyboardInputComponent.getDirection(e.getCode());
//	
//			//EDIT HERE
//			myEntity.getComponent()
//			physics.setXVel(direction.getX() * physics.getXVel());
//		});	
	
}
	
	
	private void step (double elapsedTime) {
		keyboardSystem.act(elapsedTime); //update position
		movementSystem.act(elapsedTime); //update position

		//update the sprite
		PositionComponent myEntityPosition = (PositionComponent) myEntity.getComponent(PositionComponent.class);
		PhysicsComponent myEntityPhysics = (PhysicsComponent) myEntity.getComponent(PhysicsComponent.class);
		myEntityImage.setX(myEntityPosition.getX() + myEntityPhysics.getCurrXVel() * elapsedTime);
		myEntityImage.setY(myEntityPosition.getY() + myEntityPhysics.getCurrYVel() * elapsedTime);


	}
	
	/**
	 * The main function to be called.
	 * 
	 * @param args
	 */
	public static void main (String[] args) {
		launch(args);
	}




}
