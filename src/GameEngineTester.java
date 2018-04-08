import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game_engine.Entity;
import game_engine.Vector;
import game_engine.components.KeyboardMovementInputComponent;
import game_engine.components.MovementInputComponent;
import game_engine.components.Sprite;
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
	
	//Constants - make sure there are no magic numbers!
	private static final String TITLE = "Game Engine Test";
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;

	

	@Override
	public void start(Stage stage) throws Exception {
		myStage = stage;
		
		myScene = setStage(WIDTH, HEIGHT, Color.AZURE); // include this in an initialize function
		myStage.setScene(myScene);
        myStage.setTitle(TITLE);

        myStage.show();

		KeyFrame frame = new KeyFrame(Duration.millis(1000/60),
				e -> step(1/60));
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
		//scene.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
		
		
		//TESTS BELOW
		testSpriteAndMovement(scene);
		
		
		return scene;
	}
	
	private void testSpriteAndMovement(Scene scene) {
		//TEST SPRITES
		Entity myEntity = new Entity(); // Create entity
		
		Map<Entity, String> spriteMap = new HashMap<>(); //Simulate authoring env. map of Entity to Sprite filename
		
		ArrayList<String> spriteArgs = new ArrayList<>();
		spriteArgs.add("turtle.GIF");
		spriteArgs.add("true");
		Sprite spriteComponent = new Sprite(spriteArgs); //Create sprite
		myEntity.addComponent(spriteComponent); //Add sprite component to entity
		
		spriteMap.put(myEntity, spriteComponent.getFileName());
		
		//Use Imageview to display sprite
		ImageView myEntityImage = new ImageView();
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(spriteMap.get(myEntity)));
		myEntityImage.setImage(image);
		myEntityImage.setFitWidth(40);
		myEntityImage.setFitHeight(40);
		myRoot.getChildren().add(myEntityImage);
		
		//Movement Input Componenet		
		KeyboardMovementInputComponent keyboardInputComponent = new KeyboardMovementInputComponent(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.UP, KeyCode.DOWN);
		myEntity.addComponent(keyboardInputComponent);
		scene.setOnKeyPressed(e -> {
			Vector direction = keyboardInputComponent.getDirection(e.getCode());
			
			//EDIT HERE
			myEntity.getComponent()
			physics.setXVel(direction.getX() * physics.getXVel());
		});		
		
	}
	
	private void step (double elapsedTime) {
		
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
