package game_player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.Level;
import game_engine.components.PositionComponent;
import game_engine.components.SpriteComponent;
import game_engine.components.physics.XPhysicsComponent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
	private PulldownFactory pullDownFactory;
	private Engine myEngine;
	
	private Map<ImageView, Entity> spriteMap;
	private Stage myStage;
	private Group root;

	public PlayerView(PulldownFactory pdf, Engine engine, Stage stage) {
		pullDownFactory = pdf;
		myEngine = engine;
		myStage = stage;
		root = new Group();
		instantiate(null);
		animationFrame();
	}

	public void instantiate(List<Level> levels) {
		Scene scene = new Scene(root);
		myStage.setScene(scene);
//		for(Entity e: levels.get(0).getEntities()){
//			myEngine.addEntity(e);
//		}
		root.getChildren().add(new Text("HELLOW ORLD"));
		testingStuffInitializeEntity();
		
		spriteMap = new HashMap<ImageView, Entity>();
		List<Entity> spriteEntities = myEngine.getEntitiesContaining(Arrays.asList(SpriteComponent.class, PositionComponent.class));
		System.out.println(spriteEntities.size());
		for(Entity e: spriteEntities){
			String imageName = ((SpriteComponent) e.getComponent(SpriteComponent.class)).getFileName();
			Image image = new Image(imageName);
			ImageView imageView = new ImageView(image);
			spriteMap.put(imageView, e);
			root.getChildren().add(imageView);
		}
		myStage.show();
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
		updateGame();
		render();
		handleUI();
		System.out.println(root.getChildren().size());
	}
	
	private void render(){
		for(ImageView imageView : spriteMap.keySet()){
			System.out.println("rendered");
			Entity entity = spriteMap.get(imageView);
			PositionComponent position = (PositionComponent )entity.getComponent(PositionComponent.class);
			SpriteComponent sprite = (SpriteComponent )entity.getComponent(SpriteComponent.class);
			imageView.setX(position.getX() - sprite.getWidth()/2);
			imageView.setY(position.getY() - sprite.getHeight()/2);
			System.out.println("x: " + imageView.getX() + " y: " + imageView.getY());
			imageView.setRotate(sprite.getAngle()); //TODO: figure out logistics of rotate
		}
	}
	
	//testing code
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
	
	private void updateGame() {
		
	}
	
	private void testingStuffInitializeEntity(){
		//Create one entity
		Entity myEntity = new Entity();
		myEngine.addEntity(myEntity);
		List<String> spriteArgs = new ArrayList<>();
		spriteArgs.add("turtle.GIF");
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
		xPhysicsArgs.add("0"); //X velocity aka maxX velocity aka dx (the distance it moves each step)
		xPhysicsArgs.add("0"); //acceleration
		XPhysicsComponent yPhysicsComponent = new XPhysicsComponent(xPhysicsArgs);
		yPhysicsComponent.setCurrVel(10);
		myEntity.addComponent(yPhysicsComponent);
	}
	
	

}
