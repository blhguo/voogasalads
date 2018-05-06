package authoring.loadingviews;

import java.util.List;
import java.util.Map;

import frontend_utilities.ButtonFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.keys.AuthRes;

/**
 * Extends BaseLoader. Creates the Pane that allows the user to visually select a game to load into the Authoring Environment (instead of choosing from a 
 * FileChooser. 
 * @author Jennifer Chin
 *
 */

public class AuthoringLoader extends BaseLoader {

	/**
	 * Constructor that takes in a stage.
	 * @param stage
	 */
	public AuthoringLoader(Stage stage) {
		super(stage);
	}

	/**
	 * GUIGridPane Super method. Adds a specific title to the Pane and calles BaseLoader methods.
	 */
	@Override
	public Pane finishScene(GridPane gridpane) {
		addTitle(gridpane, AuthRes.getString("Edit"));
		return addCoreFinishingElements(gridpane);
	}

	/**
	 * BaseLaoder method. Updates the pane with a gallery view of created games. When one of the thumbnail buttons is clicked, the Authoring Environment
	 * is loaded with that particular game. Each button calls ManipData method loadGame and updates the Authoring Environment
	 */
	@Override
	public void buildThumbnails(VBox vb, List<Map<String, String>> gameInfo) {
		int gameCount = 0;
		HBox row;
		for (Map<String, String> game: gameInfo){
			if (gameCount % AuthRes.getInt("ThumbnailSpacing") != 0){
				row = (HBox) vb.getChildren().get(vb.getChildren().size() - 1);
				vb.getChildren().remove(vb.getChildren().size() - 1);
			}
			else {
				row = new HBox(AuthRes.getInt("Padding"));
			}
			Button b = ButtonFactory.makeThumbnail(game.get(AuthRes.getString("ThumbImage")), game.get(AuthRes.getString("ThumbName")), e -> {
						ae.getLoader().loadGame(game.get(AuthRes.getString("ThumbGame")), game.get(AuthRes.getString("ThumbMeta")));
						myStage.getScene().setRoot(ae.display());
						myStage.show();
					}, AuthRes.getInt("ThumbnailWidth"), AuthRes.getInt("ThumbnailHeight"));
			b.getStyleClass().add("button-thumb");
			row.getChildren().add(b);
			vb.getChildren().add(row);
			gameCount++;
		}
	}
	
}
