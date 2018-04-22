package frontend_utilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import resources.keys.AuthRes;

/**
 * @author Liam Pulsifer
 * @author Jennifer Chin
 * Button Factory class allows for standardization of all Buttons across the authoring
 * environment. Used by all front end classes to make general buttons. This class sets
 * the CSS for the buttons
 */
public class ButtonFactory {

	/**
	 * Creates a new Hbox with a node on the left side, a title on the right, and a subtitle beneath
	 * @param title Desired title
	 * @param subtitle Desired subtitle
	 * @param node The node to be included
	 * @return the HBox
	 */
	public static HBox makeHBox(String title, String subtitle, Node node){
		if (node instanceof Button)
			node.getStyleClass().add("button-splash");

		VBox vb = new VBox(AuthRes.getInt("Padding"));
		vb.setMaxHeight(30);
		Text label = new Text(title);
		label.getStyleClass().add("button-label");
		vb.getChildren().add(label);
		vb.setAlignment(Pos.CENTER_LEFT);
		
		if(subtitle!=null) {
			Text subLabel = new Text(subtitle);
			subLabel.getStyleClass().add("button-sublabel");
			vb.getChildren().add(subLabel);
		}

		HBox hb = new HBox(AuthRes.getInt("HBPadding"));
		hb.setAlignment(Pos.CENTER_LEFT);
		hb.getChildren().addAll(node, vb);
		return hb;
	}

	/**
	 * Generic of the above -- creates with dummy button
	 * @param title
	 * @param subtitle
	 * @return HBox
	 */
	public static HBox makeHBox(String title, String subtitle){
		Button button = new Button("+");
		return makeHBox(title, subtitle, button);
	}

	/**
	 * Defines a new button with a specified on-action behavior
	 * @param handler is the EventHandler that we want the button to call on click
	 * @return the button
	 */
	public static Button makeButton(EventHandler<ActionEvent> handler){
		Button retButton = new Button("+");
		retButton.setOnAction(handler);
		return retButton;
	}

	/**
	 * Creates a new button with a string, an ImageView, and an EventHandler
	 * @param s String to label button
	 * @param iv ImageView to label button
	 * @param handler behavior of button
	 * @return the button
	 */
	public static Button makeButton(String s, ImageView iv, EventHandler<ActionEvent> handler){
		Button retButton = new Button(s, iv);
		retButton.setOnAction(handler);
		return retButton;
	}

	/**
	 *  Creates a new button with the specified characters
	 * @param s String
	 * @param iv ImageView
	 * @param handler Desired button action
	 * @param styleclass Style sheet to be used
	 * @return the button
	 */
	public static Button makeButton(String s, ImageView iv,
	                                EventHandler<ActionEvent> handler, String styleclass){
		Button retButton = new Button(s, iv);
		retButton.setOnAction(handler);
		retButton.getStyleClass().add(styleclass);
		return retButton;
	}

	public static HBox makeLittleHBox(String delete_entity, Object o, Button removeButton) {
		removeButton.setMaxHeight(10);
		HBox ret = makeHBox(delete_entity, (String) o, removeButton);
		ret.setMaxHeight(10);
		return ret;
	}

	public static HBox makeHBox(String create_entity, Object o, Button button, Pos center) {
		HBox box = makeHBox(create_entity, (String) o, button);
		box.setAlignment(center);
		return box;
	}
}
