package game_engine.test;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.JumpComponent;
import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.components.keyboard.KeyboardMovementInputComponent;
import game_engine.components.physics.XPhysicsComponent;
import game_engine.components.physics.YPhysicsComponent;
import game_engine.systems.InputGarbageCollectionSystem;
import game_engine.systems.MovementSystem;
import game_engine.systems.keyboard.KeyboardJumpSystem;
import game_engine.systems.keyboard.KeyboardMovementSystem;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class MovementTest extends Application{

	private Stage myStage;
	private Group myRoot;
	private Scene myScene;
	
	private static final String GRAVITY = "1000"; //effects of gravity
	private static final String JUMP_VELOCITY = "-500"; //effects of how high you jump

	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private static final String TITLE = "Game Engine Test";
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private Engine myEngine;
	private MovementSystem movementSystem;
	private KeyboardMovementSystem keyboardMovementSystem;
	private KeyboardJumpSystem keyboardJumpSystem;
	private InputGarbageCollectionSystem inputGarbageCollectionSystem;

	private Entity myEntity;
	private ImageView myEntityImage;


	@Override
	public void start(Stage stage) throws Exception {		
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
		myEngine = new Engine();

		//Create Systems
		movementSystem = new MovementSystem(myEngine);
		keyboardMovementSystem = new KeyboardMovementSystem(myEngine);
		keyboardJumpSystem = new KeyboardJumpSystem(myEngine);
		inputGarbageCollectionSystem = new InputGarbageCollectionSystem(myEngine);
		

		//Create one entity
		myEntity = new Entity();
		myEngine.addEntity(myEntity);

		myRoot = new Group();
		Scene scene = new Scene(myRoot, width, height, background);
		scene.setOnKeyPressed(e -> myEngine.receiveInput(e));
		scene.setOnKeyReleased(e -> myEngine.receiveInput(e));

		initialSprite();
		initialMovement();


		return scene;
	}

	private void initialSprite() {
		//TEST SPRITES		
		Map<Entity, String> spriteMap = new HashMap<>(); //Simulate authoring env. map of Entity to Sprite filename

		List<String> spriteArgs = new ArrayList<>();
		spriteArgs.add("turtle.GIF");
		spriteArgs.add("true"); //is visible
		spriteArgs.add("40");
		spriteArgs.add("40");
		spriteArgs.add("0"); //angle

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
		List<String> yPhysicsArgs = new ArrayList<>();
		yPhysicsArgs.add("0"); //X velocity aka maxX velocity aka dx (the distance it moves each step)
		yPhysicsArgs.add(GRAVITY); //acceleration
		YPhysicsComponent xPhysicsComponent = new YPhysicsComponent(yPhysicsArgs);
		myEntity.addComponent(xPhysicsComponent);

		List<String> xPhysicsArgs = new ArrayList<>();
		xPhysicsArgs.add("400"); //X velocity aka maxX velocity aka dx (the distance it moves each step)
		xPhysicsArgs.add("0"); //acceleration
		XPhysicsComponent yPhysicsComponent = new XPhysicsComponent(xPhysicsArgs);
		yPhysicsComponent.setCurrVel(0);
		myEntity.addComponent(yPhysicsComponent);

		//Position Component
		List<String> positionArgs = new ArrayList<>();
		positionArgs.add("100"); //X position
		positionArgs.add("100"); //Y 
		positionArgs.add("100"); //angle
		PositionComponent positionComponent = new PositionComponent(positionArgs);
		myEntity.addComponent(positionComponent);

		//Jump Component
		List<String> jumpArgs = new ArrayList<String>();
		jumpArgs.add("true");
		jumpArgs.add("2"); //number of jumps
		jumpArgs.add(JUMP_VELOCITY); //
		// Y velocity
		JumpComponent jumpComponent= new JumpComponent(jumpArgs);
		myEntity.addComponent(jumpComponent);

		//Jump Input Component
		ArrayList<String> jumpInputArgs = new ArrayList<String>();
		jumpInputArgs.add(KeyCode.UP.toString()); // Press UP for jump
		KeyboardJumpInputComponent keyboardJumpInputComponent = new KeyboardJumpInputComponent(jumpInputArgs);
		myEntity.addComponent(keyboardJumpInputComponent);

	}


	private void step (double elapsedTime) {
		keyboardJumpSystem.act(elapsedTime); //update jump
		keyboardMovementSystem.act(elapsedTime); //update position
		movementSystem.act(elapsedTime); //update position
		inputGarbageCollectionSystem.act(elapsedTime);

		//update the sprite
		PositionComponent myEntityPosition = (PositionComponent) myEntity.getComponent(PositionComponent.class);
		XPhysicsComponent myXEntityPhysics = (XPhysicsComponent) myEntity.getComponent(XPhysicsComponent.class);
		YPhysicsComponent myYEntityPhysics = (YPhysicsComponent) myEntity.getComponent(YPhysicsComponent.class);
		JumpComponent myEntityJump = (JumpComponent) myEntity.getComponent(JumpComponent.class);

		myEntityImage.setX(myEntityPosition.getX() + myXEntityPhysics.getCurrVel() * elapsedTime);
		myEntityImage.setY(myEntityPosition.getY() + myYEntityPhysics.getCurrVel() * elapsedTime);
		
		//HARDCODE COLLISION
		if (myEntityImage.getBoundsInLocal().getMaxY() >= HEIGHT) {
			myEntityPosition.setY(HEIGHT-myEntityImage.getFitHeight());
			myEntityImage.setY(myEntityPosition.getY());
			myEntityJump.setOnGround(true);
			myEntityJump.setJumpsAllowed(myEntityJump.getDefaultJumpsAllowed());
		}
		else{
			myEntityJump.setOnGround(false);
		}

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
