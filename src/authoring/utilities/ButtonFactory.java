package authoring.utilities;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import resources.keys.AuthRes;

public class ButtonFactory {


	public static HBox makeHBox(String title, String subtitle, Button button){
		button.getStyleClass().add("button-splash");

		VBox vb = new VBox(AuthRes.getInt("Padding"));
		vb.setMaxHeight(30);
		Text label = new Text(title);
		label.getStyleClass().add("button-label");
		Text subLabel = new Text(subtitle);
		subLabel.getStyleClass().add("button-sublabel");
		vb.getChildren().addAll(label, subLabel);

		HBox hb = new HBox(AuthRes.getInt("HBPadding"));
		hb.setAlignment(Pos.CENTER_LEFT);
		hb.getChildren().addAll(button, vb);
		return hb;
	}
	public static HBox makeHBox(String title, String subtitle){
		Button button = new Button("+");
		return makeHBox(title, subtitle, button);
	}
}
