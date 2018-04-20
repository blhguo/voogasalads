package game_engine.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.components.DefaultNumberOfJumpsComponent;
import game_engine.components.NumberOfJumpsAllowedComponent;
import game_engine.components.OnGroundComponent;
import game_engine.components.keyboard.KeyboardJumpInputComponent;
import game_engine.components.keyboard.LeftKeyboardComponent;
import game_engine.components.keyboard.RightKeyboardComponent;
import game_engine.components.physics.DefaultXVelComponent;
import game_engine.components.physics.DefaultYVelComponent;
import game_engine.components.physics.XVelComponent;
import game_engine.components.physics.YAccelComponent;
import game_engine.components.physics.YVelComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.VisibilityComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.systems.InputGarbageCollectionSystem;
import game_engine.systems.PositionSystem;
import game_engine.systems.VelocitySystem;
import game_engine.systems.keyboard.KeyboardJumpSystem;
import game_engine.systems.keyboard.LeftKeyboardMovementSystem;
import game_engine.systems.keyboard.RightKeyboardMovementSystem;
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

public class MovementTestGenerics extends Application{

	private Stage myStage;
	private Group myRoot;
	private Scene myScene;
	
	private static final String GRAVITY = "-1000"; //effects of gravity
	private static final String JUMP_VELOCITY = "500"; //effects of how high you jump

	private static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

	private static final String TITLE = "Game Engine Test";
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	private Engine myEngine;
	private PositionSystem positionSystem;
	private VelocitySystem velocitySystem;

	private LeftKeyboardMovementSystem leftKeyboardMovementSystem;
	private RightKeyboardMovementSystem rightKeyboardMovementSystem;

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
		positionSystem = new PositionSystem(myEngine);
		
		leftKeyboardMovementSystem = new LeftKeyboardMovementSystem(myEngine);
		rightKeyboardMovementSystem = new RightKeyboardMovementSystem(myEngine);

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
		
		FilenameComponent filename = new FilenameComponent("Mario.GIF");
		HeightComponent height = new HeightComponent("40");
		WidthComponent width = new WidthComponent("40");
		VisibilityComponent visible = new VisibilityComponent("true");
		myEntity.addComponent(filename); 
		myEntity.addComponent(height); 
		myEntity.addComponent(width); 
		myEntity.addComponent(visible); 
		
		spriteMap.put(myEntity, filename.getValue());


		//Use Imageview to display sprite
		myEntityImage = new ImageView();
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(spriteMap.get(myEntity)));
		myEntityImage.setImage(image);
		myEntityImage.setFitWidth(width.getValue());
		myEntityImage.setFitHeight(height.getValue());
		myRoot.getChildren().add(myEntityImage);	
	}

	private void initialMovement() {
		//Movement Input Componenet		
		LeftKeyboardComponent leftKeyboardInputComponent = new LeftKeyboardComponent("A");
		RightKeyboardComponent rightKeyboardInputComponent = new RightKeyboardComponent("D");

		myEntity.addComponent(leftKeyboardInputComponent);
		myEntity.addComponent(rightKeyboardInputComponent);



//		//Physics Component
//		List<String> yPhysicsArgs = new ArrayList<>();
//		yPhysicsArgs.add(JUMP_VELOCITY);
//		yPhysicsArgs.add(GRAVITY); //acceleration
		YVelComponent yVel = new YVelComponent(JUMP_VELOCITY); // 
		DefaultYVelComponent defaultY = new DefaultYVelComponent(JUMP_VELOCITY);
		myEntity.addComponent(yVel);
		myEntity.addComponent(defaultY);

		
		

//		List<String> xPhysicsArgs = new ArrayList<>();
//		xPhysicsArgs.add("400"); //X velocity aka maxX velocity aka dx (the distance it moves each step)
//		xPhysicsArgs.add("0"); //acceleration
//		XPhysicsComponent yPhysicsComponent = new XPhysicsComponent(xPhysicsArgs);
		XVelComponent xVel = new XVelComponent("0");
		DefaultXVelComponent defaultX = new DefaultXVelComponent("400");
		myEntity.addComponent(xVel);
		myEntity.addComponent(defaultX);
		
		YAccelComponent yAccel = new YAccelComponent(GRAVITY);
		myEntity.addComponent(yAccel);

		//Position Component
//		List<String> positionArgs = new ArrayList<>();
//		positionArgs.add("100"); //X position
//		positionArgs.add("100"); //Y 
//		positionArgs.add("100"); //angle
//		PositionComponent positionComponent = new PositionComponent(positionArgs);
		
		XPosComponent xPos = new XPosComponent("100");
		YPosComponent yPos = new YPosComponent("100");
		myEntity.addComponent(xPos);
		myEntity.addComponent(yPos);

		//Jump Component
		List<String> jumpArgs = new ArrayList<String>();
		jumpArgs.add("true");
		jumpArgs.add("2"); //number of jumps
		// Y velocity
		NumberOfJumpsAllowedComponent numJumps = new NumberOfJumpsAllowedComponent("2");
		DefaultNumberOfJumpsComponent defaultJumps = new DefaultNumberOfJumpsComponent("2");
		myEntity.addComponent(numJumps);
		myEntity.addComponent(defaultJumps);


		//Jump Input Component
		ArrayList<String> jumpInputArgs = new ArrayList<String>();
		jumpInputArgs.add(KeyCode.UP.toString()); // Press UP for jump
		KeyboardJumpInputComponent keyboardJumpInputComponent = new KeyboardJumpInputComponent("W");
		myEntity.addComponent(keyboardJumpInputComponent);

	}


	private void step (double elapsedTime) {
		positionSystem.act(elapsedTime);
		leftKeyboardMovementSystem.act(elapsedTime);
		rightKeyboardMovementSystem.act(elapsedTime);
		keyboardJumpSystem.act(elapsedTime);
		inputGarbageCollectionSystem.act(elapsedTime);

		//update the sprite
//		PositionComponent myEntityPosition = (PositionComponent) myEntity.getComponent(PositionComponent.class);
//		XPhysicsComponent myXEntityPhysics = (XPhysicsComponent) myEntity.getComponent(XPhysicsComponent.class);
//		YPhysicsComponent myYEntityPhysics = (YPhysicsComponent) myEntity.getComponent(YPhysicsComponent.class);
//		JumpComponent myEntityJump = (JumpComponent) myEntity.getComponent(JumpComponent.class);

		XPosComponent xPos = (XPosComponent) myEntity.getComponent(XPosComponent.class);
		YPosComponent yPos = (YPosComponent) myEntity.getComponent(YPosComponent.class);
		XVelComponent xVel = (XVelComponent) myEntity.getComponent(XVelComponent.class);
		YVelComponent yVel = (YVelComponent) myEntity.getComponent(YVelComponent.class);
		YAccelComponent yAccel = (YAccelComponent) myEntity.getComponent(YAccelComponent.class);
		NumberOfJumpsAllowedComponent numJumps = new NumberOfJumpsAllowedComponent("2");
		DefaultNumberOfJumpsComponent defaultJumps = new DefaultNumberOfJumpsComponent("2");

		
		myEntityImage.setX(xPos.getValue() + xVel.getValue() * elapsedTime);
		myEntityImage.setY(yPos.getValue() + yVel.getValue() * elapsedTime);
		
		//HARDCODE COLLISION
		if (myEntityImage.getBoundsInLocal().getMaxY() >= HEIGHT) {
			yPos.setValue(HEIGHT-myEntityImage.getFitHeight());
			myEntityImage.setY(yPos.getValue());
			numJumps.setValue(defaultJumps.getValue());
		}
		else{
			//myEntityJump.setOnGround(false);
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
