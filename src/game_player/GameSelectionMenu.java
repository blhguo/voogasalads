package game_player;

import authoring.GameChooserScreen;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameSelectionMenu {
	
	private Button gameSelectionButton;
	private PulldownFactory pullDownFactory;
	
	protected void makeGameSelectionMenu(HBox pane) {
		ImageView gameImageView = new ImageView( getClass().getResource( "/game_player_resources/game.png").toExternalForm());
		gameImageView.setFitHeight(30);
		gameImageView.setFitWidth(30);
		gameSelectionButton = new Button("", gameImageView);
		gameSelectionButton.getStyleClass().add("button-nav");
		gameSelectionButton.setOnAction(click->{showGameSelectionMenu();});
		pane.getChildren().add(gameSelectionButton);
		
	}
	
	
	
	protected void showGameSelectionMenu() {
		pullDownFactory.importGame();
	}	


}
