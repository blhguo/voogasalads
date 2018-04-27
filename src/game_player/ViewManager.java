package game_player;

import java.io.File;

import authoring.loadingviews.PlayerLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.effect.ColorAdjust;
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
import javafx.stage.Stage;

/**
 * This class initializes the layout for the game player, and manages the
 * structure of the overall program.
 * 
 * @author Brandon Dalla Rosa, Dana Park
 *
 */
public class ViewManager {
	private Menu menu;
	private Stage gameStage;
	private double sceneWidth = 1200;
	private double sceneHeight = 900;
	private Paint backColor = Color.BLACK;
	private Pane view;
	private Scene gameScene;
	private PulldownFactory pullDownFactory;
	private ImageView gameImageView;
	private Image gameBackground;
	private BackgroundImage game;
	private SubScene subScene;
	private Group subRoot;
	private ColorAdjust colorAdjust = new ColorAdjust();
	
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
		order.getStyleClass().add("pane-back");

		order.setAlignment(Pos.CENTER);
		center.getChildren().add(order);
		
		menu.addMenu(order);
		view = new Pane();
		view.setPrefSize(1000, 730);
		subRoot = new Group();
		subScene = new SubScene(subRoot, 770, 530, false, null);

		game = new BackgroundImage(gameBackground, BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		view.setBackground(new Background(game));

		order.getChildren().add(subScene);
		subRoot.getChildren().add(view);
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
				
				colorAdjust.setBrightness((double) new_val);
				gameImageView.setEffect(colorAdjust);
				System.out.println(new_val);
			}
		});
	}
    
    /**
     * Changes the volume of the current program.
     */ 
	public void changeVolume() {
		this.menu.getVolumeSlider().valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				String path = "/resources/baby.mp3";
				Media media = new Media(new File(path).toURI().toString());

				MediaPlayer mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setVolume((double) new_val);
				mediaPlayer.play();

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
}
