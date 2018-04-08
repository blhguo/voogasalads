package gameplayer;

import authoring.GameChooserScreen;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
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
	
	public ViewManager(Menu menu, Stage stage) {
		this.menu = menu;
		this.gameStage = stage;
		setScene();
		gameStage.setTitle("CALL US SALAD");
		gameStage.show();
	}
	
	private void setScene() {
		Pane pane = setObjects();
		Scene scene = new Scene(pane,sceneWidth,sceneHeight);
		scene.getStylesheets().add(getClass().getResource("playerAesthetic.css").toString());
		gameStage.setScene(scene);
	}
	
	private Pane setObjects() {
		HBox center = new HBox(30);
		center.setAlignment(Pos.CENTER);
		BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		center.setBackground(new Background(back));
		VBox order = new VBox(20);
		order.getStyleClass().add("pane-back");
		order.setAlignment(Pos.CENTER);
		center.getChildren().add(order);
		menu.addMenu(order);
		view = new Pane();
		view.setPrefSize(770, 530);
		BackgroundImage game = new BackgroundImage(new Image("background1.jpg"), BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		view.setBackground(new Background(game));
		order.getChildren().add(view);
		order.setBackground(new Background(new BackgroundFill(backColor,null,null)));
		return center;
	}
	
	public void showGameSelectionMenu() {
		GameChooserScreen gc = new GameChooserScreen(gameStage);
		gameStage.setScene(gc.display());
		gameStage.show();
	}
	
	public Pane getNode() {
		return view;
	}
}
