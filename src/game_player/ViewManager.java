package game_player;


import authoring.GUI_Heirarchy.GUIBuilder;
import authoring.loadingviews.PlayerLoader;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.*;
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
	private MediaPlayer sound;
	private Text coins;
	private Text time;
	private int coinCount=3;
	private int timeCount=60;
	
	/**
	 * Constructor for the view manager. It initializes all of the structures
	 * seen in the game player and organizes them efficiently.
	 * @param menu: The current menu of the program.
	 * @param stage: The active stage hosting the game.
	 * @param pdf: The active pull down factory.
	 */ 
	public ViewManager() {
		//TODO something
	}
	
	public void initialize(InstanceStorage storage) {
		menu = storage.getMenu();
		gameStage = storage.getStage();
		setScene();
		gameStage.setTitle("CALL US SALAD");
		gameStage.setFullScreen(true);
		gameStage.show();
		changeBrightness();
		changeVolume();
	}

	private void setScene() {
		Pane pane = setObjects();
		gameScene = new Scene(pane,sceneWidth,sceneHeight);
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

	private Pane setObjects() {
		HBox center = new HBox(30);
		center.setAlignment(Pos.CENTER);
		
		gameBackground = new Image("gray.png");
		gameImageView = new ImageView();
		gameImageView.setImage(gameBackground);

		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		center.setBackground(new Background(back));

		VBox order = new VBox(20);

		order.setAlignment(Pos.CENTER);
		center.getChildren().add(order);
		
		view = new Pane();
		view.setPrefSize(1000, 730);
		subRoot = new Group();
		subScene = new SubScene(subRoot, SUBSCENE_WIDTH, SUBSCENE_HEIGHT, false, null);
		coins = createText(coins,5,15,"coins collected: "+coinCount, 16);
		time = createText(time, 150, 15, "time: "+timeCount,16);
		game = new BackgroundImage(gameBackground, BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		view.setBackground(new Background(game));

		order.getChildren().add(subScene);
		subRoot.getChildren().add(view);
		menu.addMenu(order);
		
		Media soundFile = new Media(getClass().getResource("song.mp3").toExternalForm());
		sound = new MediaPlayer(soundFile);
		sound.play();
		sound.setVolume(0);
		sound.setCycleCount(MediaPlayer.INDEFINITE);
		order.setBackground(new Background(new BackgroundFill(backColor,null,null)));
		subRoot.getChildren().add(coins);
		subRoot.getChildren().add(time);
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
		BackgroundImage back = new BackgroundImage(new Image("mountain.png"), BackgroundRepeat.REPEAT,
		BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		view.setBackground(new Background(back));
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
     * Display the stage for game selection.
     */ 
	public void showGameSelectionMenu() {
		gameStage.getScene().setRoot(new PlayerLoader(gameStage).display());
		gameStage.show();
	}

	/**
	 * Return the root node of the view manager.
	 */ 
	public Pane getNode() {
		return view;
	}

	@Override
	public Pane display() {
		return mainHBox;
	}
	
	private Text createText(Text txt, int x, int y, String message, int fontSize) {
		txt = new Text();
		txt.setX(x);
		txt.setY(y);
		txt.setFill(Color.WHITE);
		txt.setText(message);
		txt.setFont(Font.font("Segouei", fontSize));
		return txt;
	}
}
