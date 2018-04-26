package game_player;

import java.io.File;

import authoring.GameChooserScreen;
import authoring.GUI_Heirarchy.GUIBuilder;
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
import javafx.scene.shape.Rectangle;
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
	private Menu menu;
	private Stage gameStage;
	private double sceneWidth = 1200;
	private double sceneHeight = 900;
	private Paint backColor = Color.TRANSPARENT;
	private Pane view;
	private Scene gameScene;
	private PulldownFactory pullDownFactory;
	private ImageView gameImageView;
	private Image gameBackground;
	private BackgroundImage game;
	private SubScene subScene;
	private Group subRoot;
	private Pane mainHBox;
	private Rectangle dimmer;
	private Paint dimmerColor = Color.BLACK;
	private MediaPlayer sound;
	private Text score;
	private int scoreCount = 3;
	private int timeCount = 60;

	private Text time;
	
	/**
	 * Constructor for the view manager. It initializes all of the structures
	 * seen in the game player and organizes them efficiently.
	 * @param menu: The current menu of the program.
	 * @param stage: The active stage hosting the game.
	 * @param pdf: The active pull down factory.
	 */ 
	public ViewManager(Menu menu, Stage stage, PulldownFactory pdf) {
		this.menu = menu;
		this.pullDownFactory = pdf;
		pullDownFactory.setViewManager(this);
		this.gameStage = stage;
		setScene();
		gameStage.setTitle("CALL US SALAD");
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
		subScene = new SubScene(subRoot, 920, 660, false, null);

		game = new BackgroundImage(gameBackground, BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		view.setBackground(new Background(game));

		order.getChildren().add(subScene);
		subRoot.getChildren().add(view);
		
		dimmer = new Rectangle(0,0,5000,5000);
		dimmer.setFill(dimmerColor);
		dimmer.setManaged(false);
		dimmer.setOpacity(0.0);
		order.getChildren().add(dimmer);
		menu.addMenu(order);
		
		Media soundFile = new Media(getClass().getResource("song.mp3").toExternalForm());
		sound = new MediaPlayer(soundFile);
		sound.play();
		sound.setVolume(0);
		sound.setCycleCount(sound.INDEFINITE);
		order.setBackground(new Background(new BackgroundFill(backColor,null,null)));
		
		score = createText(score,  5, 382, "lives remaining: "+scoreCount, 12) ;
		time = createText(time,  5, 395, "bricks hit: "+timeCount, 12) ;
		order.getChildren().add(score);
		order.getChildren().add(time);

		return center;
	}
	
	private Text createText(Text txt, int x, int y, String message, int fontSize) {
		txt = new Text();
		txt.setX(x);
		txt.setY(y);
		txt.setFill(Color.WHITE);
		txt.setText(message);
		txt.setFont(Font.font("Impact", fontSize));
		return txt;
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
				dimmer.opacityProperty().set(1-(double)new_val);
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
		GameChooserScreen gc = new GameChooserScreen(gameStage);
		//gameStage.setScene(gc.display());
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
}
