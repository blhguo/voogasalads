package gameplayer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	
	public ViewManager(Menu menu, Stage stage) {
		this.menu = menu;
		this.gameStage = stage;
		setScene();
		gameStage.setTitle("CALL US SALAD");
	}
	
	private void setScene() {
		Pane pane = setObjects();
		Scene scene = new Scene(pane,sceneWidth,sceneHeight,backColor);
		gameStage.setScene(scene);
	}
	
	private Pane setObjects() {
		HBox center = new HBox(20);
		center.setAlignment(Pos.CENTER);
		VBox order = new VBox(10);
		order.setAlignment(Pos.CENTER);
		center.getChildren().add(order);
		return center;
		//TODO Populate these boxes with the menu and view
	}
}
