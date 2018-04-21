package voogasalad.example.voogle_images;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point into demo application.
 * 
 * @author benhubsch
 */
public class Main extends Application {
	
	private static final String DEMO = "Image Demo";

	@Override
	public void start(Stage stage) {
		DemoApp demoApp = new DemoApp();
		
		stage.setTitle(DEMO);
		stage.setScene(demoApp.getScene());
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
