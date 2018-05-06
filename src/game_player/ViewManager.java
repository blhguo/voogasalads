package game_player;

import authoring.Toolbar;
import authoring.GUI_Heirarchy.GUIBuilder;
import game_engine.level.LevelBackgroundComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class initializes the layout for the game player, and manages the
 * structure of the overall program.
 * 
 * @author Brandon Dalla Rosa, Dana Park
 *
 */
public class ViewManager extends GUIBuilder{
	public static final double SUBSCENE_WIDTH = 920;
	public static final double SUBSCENE_HEIGHT = 660;
	private static final int FONTSIZE = 16;

	private Menu menu;
	private Stage gameStage;
	private double sceneWidth = 1200;
	private double sceneHeight = 900;
	private Paint backColor = Color.TRANSPARENT;
	private Pane view;
	private Scene gameScene;
	private ImageView gameImageView;
	private Image gameBackground;
	private BackgroundImage game;
	private SubScene subScene;
	private Group subRoot;
	private Pane mainHBox;
	private AudioClip sound;
	private DataConnect dataConnect;
	private VBox order;
	/**
	 * Constructor for the view manager. Does not initialize immediately.
	 * seen in the game player and organizes them efficiently.
	 * @param menu: The current menu of the program.
	 * @param stage: The active stage hosting the game.
	 * @param pdf: The active pull down factory.
	 */ 
	public ViewManager() {
	}
	/**
	 * Method called to initialize the class after creation.
	 * @param storage
	 */
	public void initialize(InstanceStorage storage) {
		menu = storage.getMenu();
		gameStage = storage.getStage();
		dataConnect = storage.getDataConnect();
		setScene();
		gameStage.setTitle("CALL US SALAD");
		gameStage.setFullScreen(true);

		gameStage.show();
		changeBrightness();
		changeVolume();
	}
	/**
	 * Method called to set the scene of the game.
	 */
	private void setScene() {
		Pane pane = setObjects();
		gameScene = new Scene(new Toolbar(gameStage).integrateToolbar(pane), sceneWidth, sceneHeight);
		gameScene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());
		gameStage.setScene(gameScene);
		mainHBox = pane;
	}

	/**
	 * Returns the scene of the game for ease of access.
	 */ 
	public Scene getScene() {
		return gameScene;
	}
	/**
	 * Sets the desired layout for all the visible aspects of the game player.
	 * @return
	 */
	private Pane setObjects() {
		HBox center = new HBox(30);
		center.setAlignment(Pos.CENTER);

		gameBackground = new Image("gray.png");
		gameImageView = new ImageView();
		gameImageView.setImage(gameBackground);

		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		center.setBackground(new Background(back));

		order = new VBox(20);

		order.setAlignment(Pos.CENTER);
		center.getChildren().add(order);

		view = new Pane();
		view.setPrefSize(1000, 730);
		subRoot = new Group();
		subScene = new SubScene(subRoot, SUBSCENE_WIDTH, SUBSCENE_HEIGHT, false, null);
		game = new BackgroundImage(gameBackground, BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		view.setBackground(new Background(game));

		order.getChildren().add(subScene);
		subRoot.getChildren().add(view);

		menu.addMenu(order);

		order.setBackground(new Background(new BackgroundFill(backColor,null,null)));
		return center;
	}

	/**
	 * Returns the subscene in which the current active game can be found.
	 */ 
	public SubScene getSubScene() {
		return subScene;
	}

	/**
	 * Returns the root of the subscene, for entities to be added to.
	 */ 
	public Group getSubRoot() {
		return subRoot;
	}


	/**
	 * Changes the background image of the subscene to the desired image.
	 */ 
	public void changeBackground() {
		//String imagePath = dataConnect.getGameEngine().getLevel().getComponent(LevelBackgroundComponent.class).getValue();
		Image im;
		try {
			im = new Image(dataConnect.getGameEngine().getLevel().getComponent(LevelBackgroundComponent.class).getValue());
		} catch ( Exception e){
			im = new Image("mountain.png");
		}
			BackgroundImage back = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		order.setBackground(new Background(back));
	}


	/**
	 * Changes the brightness of the current program.
	 */ 
	public void changeBrightness() {
		this.menu.getBrightnessSlider().valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				gameStage.opacityProperty().set((double)new_val);
			}
		});
	}

	/**
	 * Changes the volume of the current program.
	 */ 
	public void changeVolume() {
		this.menu.getVolumeSlider().valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				sound.setVolume((double) new_val);

			}
		});
	}


	/**
	 * Return the root node of the view manager.
	 */ 
	public Pane getNode() {
		return view;
	}
	
	/**
	 * Returns the main display for use in other packages.
	 */
	@Override
	public Pane display() {
		return mainHBox;
	}

	/**
	 * Method to create the HUD text on the screen.
	 * @param x
	 * @param y
	 * @param message
	 * @return
	 */
	public Text createText(double x, double y, String message) {
		Text txt = new Text(message);
		txt.setX(x);
		txt.setY(y);
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Segouei", FONTSIZE));
		subRoot.getChildren().add(txt);
		return txt;
	}


}
