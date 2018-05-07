package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setFullScreen(true);
		SplashScreen ss = new SplashScreen(primaryStage);
		Scene testScene = ss.getScene();
		primaryStage.setScene(testScene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}