package authoring.loadingviews;

import java.util.ArrayList;
import java.util.Map;

import frontend_utilities.ButtonFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class AuthoringLoader extends BaseLoader {

	public AuthoringLoader(Stage stage) {
		super(stage);
	}

	@Override
	public Pane finishScene(GridPane gridpane) {
		addTitle(gridpane, AuthRes.getString("Edit"));
		return addCoreFinishingElements(gridpane);
	}

	@Override
	public void buildThumbnails(VBox vb, ArrayList<Map<String, String>> gameInfo) {
		int gameCount = 0;
		HBox row;
		for (Map<String, String> game: gameInfo){
			if (gameCount % 4 != 0){
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
					}, 250, 150);
			b.getStyleClass().add("button-thumb");
			row.getChildren().add(b);
			vb.getChildren().add(row);
			gameCount++;
		}
		//System.out.println("HEIGHT: " + myStage.getHeight());
	}
	
}
