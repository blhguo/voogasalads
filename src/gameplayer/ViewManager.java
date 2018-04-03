package gameplayer;

import java.awt.Panel;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * @author Brandon Dalla Rosa
 *
 */
public class ViewManager {
	private Menu menu;
	private Stage gameStage;
	private double sceneWidth = 600;
	private double sceneHeight = 600;
	private Paint backColor = Color.LIGHTBLUE;
	private Rectangle view;
	
	public ViewManager(Menu menu, Stage stage) {
		this.menu = menu;
		this.gameStage = stage;
		setScene();
		gameStage.setTitle("CALL US SALAD");
		gameStage.show();
	}
	
	private void setScene() {
		Pane pane = setObjects();
		Scene scene = new Scene(pane,sceneWidth,sceneHeight,backColor);
		gameStage.setScene(scene);
		scene.setOnKeyPressed(click->menu.checkForInput(click.getCode()));
	}
	
	private Pane setObjects() {
		HBox center = new HBox(20);
		center.setAlignment(Pos.CENTER);
		center.setBackground(new Background(new BackgroundFill(backColor,null,null)));
		VBox order = new VBox(10);
		order.setAlignment(Pos.CENTER);
		center.getChildren().add(order);
		menu.addMenu(order);
		view = new Rectangle(500,470);
		view.setFill(Color.WHITE);
		order.getChildren().add(view);
		order.setBackground(new Background(new BackgroundFill(backColor,null,null)));
		return center;
		//TODO Populate these boxes with the menu and view
	}
}
