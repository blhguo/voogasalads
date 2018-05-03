package game_player;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GameSelectionMenu {

	private Button gameSelectionButton;
	private DataConnect dataConnect;
	
	protected void makeGameSelectionMenu(HBox pane, DataConnect dc) {
		this.dataConnect = dc;
		ImageView gameImageView = new ImageView( getClass().getResource( "/game_player_resources/game.png").toExternalForm());
		gameImageView.setFitHeight(30);
		gameImageView.setFitWidth(30);
		gameSelectionButton = new Button("", gameImageView);
		gameSelectionButton.getStyleClass().add("button-nav");
		gameSelectionButton.setOnAction(click->{showGameSelectionMenu();});
		pane.getChildren().add(gameSelectionButton);

	}



	protected void showGameSelectionMenu() {
		dataConnect.importGame();
	}


}
