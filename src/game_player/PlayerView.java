package game_player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.Engine;
import game_engine.Entity;
import game_engine.Level;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.WidthComponent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * 
 * @author Dana Park Class that handles animations and updating of animations in game
 *
 */
public class PlayerView {

	private Timeline animation;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private static final double DOUBLE_RATE = 1.05;
	private static final double HALF_RATE = 0.93;
	private static final double SCENE_SIZE = 500;
	
	private PulldownFactory pullDownFactory;
	private Engine myEngine;
	private Map<ImageView, Entity> spriteMap;
	private Group root;
	private Camera cam;
	private ViewManager viewManager;
	private SubScene subScene;

	/**
	 * @param pdf
	 * @param engine
	 * @param view constructor for PlayerView
	 *
	 */
	public PlayerView(PulldownFactory pdf, Engine engine, ViewManager view) {
		pullDownFactory = pdf;
		myEngine = engine;
		viewManager = view;
	}

	/**
	 * method that instantiates the scene with the camera for the game with all necessary sprites
	 *
	 */
	public void instantiate() {
		Scene scene = viewManager.getScene();
		subScene = viewManager.getSubScene();
		root = viewManager.getSubRoot();
		scene.setOnKeyPressed(e -> {
			myEngine.receiveInput(e);
		});
		scene.setOnKeyReleased(e -> myEngine.receiveInput(e));

		subScene.setOnKeyPressed(e -> myEngine.receiveInput(e));
		subScene.setOnKeyReleased(e -> myEngine.receiveInput(e));

		cam = new ParallelCamera();
		subScene.setCamera(cam);
		List<Level> levels = pullDownFactory.getLevels();

		spriteMap = new HashMap<ImageView, Entity>();
		List<Entity> spriteEntities = levels.get(0)
				.getEntitiesContaining(Arrays.asList(FilenameComponent.class, HeightComponent.class, WidthComponent.class));
		for (Entity e : spriteEntities) {
			String imageName = e.getComponent(FilenameComponent.class).getValue();
			Double height = e.getComponent(HeightComponent.class).getValue();
			Double width = e.getComponent(WidthComponent.class).getValue();
			Image image = new Image(imageName);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(width);
			imageView.setFitHeight(height);
			spriteMap.put(imageView, e);
			root.getChildren().add(imageView);
		}

		animationFrame();
	}

	/**
	 * @param vm method that sets viewManager as the param
	 */
	public void setViewManager(ViewManager vm) {
		viewManager = vm;
	}

	private void animationFrame() {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step(double delay) {
		animation.stop();
		myEngine.update(delay);
		render();
		handleUI();
	}

	private void render() {
		for (ImageView imageView : spriteMap.keySet()) {
			Entity entity = spriteMap.get(imageView);
			Double xPos = entity.getComponent(XPosComponent.class).getValue();
			Double yPos = entity.getComponent(YPosComponent.class).getValue();
			Double width = entity.getComponent(WidthComponent.class).getValue();
			Double height = entity.getComponent(HeightComponent.class).getValue();
			imageView.setX(xPos - width / 2);
			imageView.setY(yPos - height / 2);
		}
		
		// assumes only one entity with the keyboard component
		Entity entity = myEngine.getLevel().getEntitiesContaining(Arrays.asList(XPosComponent.class, YPosComponent.class)).get(0);
		Double xPos = entity.getComponent(XPosComponent.class).getValue();
		Double yPos = entity.getComponent(YPosComponent.class).getValue();
		cam.setLayoutX(xPos - SCENE_SIZE / 2);
		cam.setLayoutY(yPos - SCENE_SIZE / 2);
	}

	/**
	 * method that handles reactions when buttons are pressed on Menu. Ex: When Play button is pressed,
	 * the method will make the game play
	 *
	 */

	public void handleUI() {
		String selectedAction = pullDownFactory.getSpeedBox().getSelectionModel().getSelectedItem();
		String statusAction = pullDownFactory.getStatusBox().getSelectionModel().getSelectedItem();

		if (selectedAction.equals("Speed Up")) {

			animation.setRate(animation.getRate() * DOUBLE_RATE);
		}
		if (selectedAction.equals("Slow Down")) {
			animation.setRate(animation.getRate() * HALF_RATE);
		}
		if (statusAction.equals("Pause Game")) {
			animation.stop();
		}
		if (statusAction.equals("Play Game")) {
			animation.play();
		}
	}

}
