package authoring;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane testBP = new BorderPane();
		Scene testScene = new Scene(testBP);
		primaryStage.setScene(testScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
