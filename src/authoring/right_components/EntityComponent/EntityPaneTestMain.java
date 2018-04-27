package authoring.right_components.EntityComponent;

import authoring.Canvas;
import authoring.controllers.EntityController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EntityPaneTestMain extends Application{


	@Override
	public void start(Stage primaryStage) throws Exception {
		EntityPane pane = new EntityPane();
		Canvas canvas = new Canvas();
		EntityController controller = new EntityController(pane, canvas);
		pane.setController(controller);
		canvas.setController(controller);
		Pane p = pane.getView();
		Scene scene = new Scene(p);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args){
		launch(args);
	}
}
