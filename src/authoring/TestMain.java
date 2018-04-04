package authoring;

import authoring.right_components.ImageBuilder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		SplashScreen ss = new SplashScreen(primaryStage);
		Scene testScene = ss.display();
		primaryStage.setScene(testScene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
