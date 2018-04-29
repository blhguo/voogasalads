package game_player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import game_engine.Component;
import game_engine.Engine;
import game_engine.Entity;
import game_engine.Tuple;
import game_engine.Vector;
import game_engine.components.PrimeComponent;
import game_engine.components.position.XPosComponent;
import game_engine.components.position.YPosComponent;
import game_engine.components.sprite.FilenameComponent;
import game_engine.components.sprite.HeightComponent;
import game_engine.components.sprite.SpritePolarityComponent;
import game_engine.components.sprite.VisibilityComponent;
import game_engine.components.sprite.WidthComponent;
import game_engine.components.sprite.ZHeightComponent;
import game_engine.level.Level;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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

	private PulldownFactory pullDownFactory;
	private Engine myEngine;
	private Map<Entity, ImageView> spriteMap;
	private Group root;
	private ViewManager viewManager;
	private SubScene subScene;
	private ParallelCamera cam;
	private DataManager dataManager;
	private boolean notSet;
	private UUID myId;

	private Entity primary;

	/**
	 * @param pdf
	 * @param engine
	 * @param view constructor for PlayerView
	 *
	 */
	public PlayerView(PulldownFactory pdf, ViewManager view, DataManager dtm) {
		pullDownFactory = pdf;
		viewManager = view;
		dataManager = dtm;
		notSet = true;
		myId = UUID.randomUUID();
	}

	public void setEngine(Engine e) {
		this.myEngine = e;
	}

	/**
	 * method that instantiates the scene with the camera for the game with all necessary sprites
	 *
	 */
	public void instantiate() {
		Scene scene = viewManager.getScene();
		subScene = viewManager.getSubScene();
		root = viewManager.getSubRoot();
		scene.setOnKeyReleased(event -> myEngine.receiveKeyInput(new Tuple<UUID, KeyEvent>(myId, event)));
		scene.setOnKeyPressed(event -> myEngine.receiveKeyInput(new Tuple<UUID, KeyEvent>(myId, event)));
		cam = new ParallelCamera();
		subScene.setCamera(cam);
		Level level = myEngine.getLevel();
		
		if (!assignId(level)) {
			System.out.println("no one assigned");
			// no players remaining to be claimed...error?
			return;
		}
		
		primary = myEngine.getLevel().getEntitiesContaining(Arrays.asList(PrimeComponent.class)).get(0);
		setGamePlayerOnce();

		spriteMap = new HashMap<>();
		List<Entity> spriteEntities = level.getEntitiesContaining(
				Arrays.asList(FilenameComponent.class, HeightComponent.class, WidthComponent.class));
		for (Entity e : spriteEntities) {
			String imageName = e.getComponent(FilenameComponent.class).getValue();
			Double height = e.getComponent(HeightComponent.class).getValue();
			Double width = e.getComponent(WidthComponent.class).getValue();
			Image image = new Image(imageName);
			ImageView imageView = new ImageView(image);
			imageView.setFitWidth(width);
			imageView.setFitHeight(height);
			spriteMap.put(e, imageView);
			root.getChildren().add(imageView);
		}

		animationFrame();
	}

	private boolean assignId(Level level) {
		for (Entity entity : level.getEntities()) {
			if (entity.hasAll(Arrays.asList(PrimeComponent.class)) && (entity.getComponent(PrimeComponent.class).getValue() == null)) {
				entity.getComponent(PrimeComponent.class).setValue(myId);
				return true;
			}
		}
		return false;
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
		myEngine.update(delay);
		render();
	}

	private void render() {
		root.getChildren().clear();

		Double xPos = primary.getComponent(XPosComponent.class).getValue();
		Double yPos = primary.getComponent(YPosComponent.class).getValue();
		cam.relocate(xPos - ViewManager.SUBSCENE_WIDTH / 2, yPos - ViewManager.SUBSCENE_HEIGHT / 2);

		// render level background

		myEngine.getLevel().getEntities().stream().filter(entity -> isInView(entity, xPos, yPos)).sorted(this::compareZ)
				.forEach(this::display);
	}

	private int compareZ(Entity a, Entity b) {
		return a.getComponent(ZHeightComponent.class).getValue()
				.compareTo(b.getComponent(ZHeightComponent.class).getValue());
	}

	private void setGamePlayerOnce() {
		if (notSet) {
			notSet = false;
			dataManager.setGamePlayer(primary);
		}
	}

	private void clickInput(ImageView imageView) {
		double middleX = imageView.getX() + imageView.getFitWidth() / 2;
		double middleY = imageView.getY() + imageView.getFitHeight() / 2;
		Vector click = new Vector(middleX, middleY);
		myEngine.receiveMouseInput(new Tuple<UUID, Vector>(myId, click));
	}

	private ImageView getImageView(Entity entity) {
		String filename = entity.getComponent(FilenameComponent.class).getValue();
		if (!spriteMap.containsKey(entity)) {
			spriteMap.put(entity, new ImageView(filename));
		}
		ImageView imageView = spriteMap.get(entity);
		imageView.setOnMousePressed(event -> clickInput(imageView));
		return imageView;
	}

	private void display(Entity entity) {
		Double xPos = entity.getComponent(XPosComponent.class).getValue();
		Double yPos = entity.getComponent(YPosComponent.class).getValue();
		Double width = entity.getComponent(WidthComponent.class).getValue();
		Double height = entity.getComponent(HeightComponent.class).getValue();
		Boolean visibility = entity.getComponent(VisibilityComponent.class).getValue();

		ImageView imageView = getImageView(entity);
		imageView.setX(xPos - width / 2);
		imageView.setY(yPos - height / 2);
		imageView.setVisible(visibility);

		Component<Integer> polarity = entity.getComponent(SpritePolarityComponent.class);
		// changes which direction the imageview faces based off of movement direction of entity
		if (polarity != null) {
			imageView.setScaleX(Math.signum(polarity.getValue()));
		}
		root.getChildren().add(imageView);
	}

	private boolean isInView(Entity entity, double centerX, double centerY) {
		double xPos = entity.getComponent(XPosComponent.class).getValue();
		double yPos = entity.getComponent(YPosComponent.class).getValue();
		double height = entity.getComponent(HeightComponent.class).getValue();
		double width = entity.getComponent(WidthComponent.class).getValue();

		double minX = xPos - width / 2;
		double maxX = xPos + width / 2;
		double minY = yPos - height / 2;
		double maxY = yPos + height / 2;

		return checkCorner(minX, minY, centerX, centerY) || checkCorner(minX, maxY, centerX, centerY)
				|| checkCorner(maxX, minY, centerX, centerY) || checkCorner(maxX, maxY, centerX, centerY);
	}

	private boolean checkCorner(double entityX, double entityY, double centerX, double centerY) {
		double sceneMinX = centerX - ViewManager.SUBSCENE_WIDTH / 2;
		double sceneMaxX = centerX + ViewManager.SUBSCENE_WIDTH / 2;
		double sceneMinY = centerY - ViewManager.SUBSCENE_HEIGHT / 2;
		double sceneMaxY = centerY + ViewManager.SUBSCENE_HEIGHT / 2;
		return ((sceneMinX <= entityX && entityX <= sceneMaxX) && (sceneMinY <= entityY && entityY <= sceneMaxY));
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
