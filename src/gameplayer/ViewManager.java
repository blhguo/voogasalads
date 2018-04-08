package gameplayer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
	private double sceneWidth = 800;
	private double sceneHeight = 600;
	private Paint backColor = Color.LIGHTBLUE;
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
		Scene scene = new Scene(pane,sceneWidth,sceneHeight,backColor);
		gameStage.setScene(scene);
	}
	
	private Pane setObjects() {
		HBox center = new HBox(20);
		center.setAlignment(Pos.CENTER);
		center.getStyleClass().add("hbox");
		//center.setBackground(new Background(new BackgroundFill(backColor,null,null)));
		VBox order = new VBox(10);
		order.setAlignment(Pos.CENTER);
		center.getChildren().add(order);
		menu.addMenu(order);
		view = new Pane();
		view.setPrefSize(770, 530);
		view.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
		order.getChildren().add(view);
		order.setBackground(new Background(new BackgroundFill(backColor,null,null)));
		return center;
	}
	
	public Pane getNode() {
		return view;
	}
}
