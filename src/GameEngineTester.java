import game_engine.Entity;
import game_engine.components.Sprite;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
		
		//TEST NEW ENTITY
		Entity myEntity = new Entity(); // create entity
		Sprite spriteComponent = new Sprite(null);
		myEntity.addComponent(spriteComponent);
		
		return scene;
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
