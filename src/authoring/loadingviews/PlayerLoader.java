package authoring.loadingviews;

import java.util.ArrayList;
import java.util.Map;

import frontend_utilities.ButtonFactory;
import game_player.PlayerMain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

public class PlayerLoader extends BaseLoader {

	public PlayerLoader(Stage stage) {
		super(stage);
	}

	@Override
	public Pane finishScene(GridPane gridpane) {
		addTitle(gridpane, AuthRes.getString("Play"));
		return addCoreFinishingElements(gridpane);
	}

	@Override
	public void buildThumbnails(VBox vb, ArrayList<Map<String, String>> gameInfo) {
		Text mtncap = new Text("   Mountain ~vIbes~");
		mtncap.getStyleClass().add("game-chooser");
		vb.getChildren().addAll(
				ButtonFactory.makeButton(null,new ImageView(new Image(AuthRes.getString("mtnthumb"))), 
						e -> new PlayerMain().start(myStage), "button-nav"),
				mtncap);
	}

}
