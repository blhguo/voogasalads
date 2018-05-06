package frontend_utilities;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
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

		VBox vb = makeVBox(title, subtitle);

		HBox hb = new HBox(AuthRes.getInt("HBPadding"));
		hb.setAlignment(Pos.CENTER_LEFT);
		hb.getChildren().addAll(node, vb);
		return hb;
	}
	
	private static VBox makeVBox(String title, String subtitle){
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
		return vb;
	}
	private static VBox makeVBox(String title, String subtitle, double wrapWidth){
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
		return vb;
	}
	
	/**
	 * Creates a HBox with title on the left side and Node on the right side
	 * @param title
	 * @param subtitle
	 * @param node
	 * @return
	 */
	public static HBox makeReverseHBox(String title, String subtitle, Node node){
		if (node instanceof Button)
			node.getStyleClass().add("button-splash");
		VBox vb = makeVBox(title, subtitle);
		HBox hb = new HBox(AuthRes.getInt("HBPadding"));
		hb.setAlignment(Pos.CENTER_LEFT);
		hb.getChildren().addAll(vb, node);
		return hb;
	}
	
	/**
	 * Creates HBox with title on left side and Node on the right side. Specifies a width for the HBox
	 * @param title
	 * @param subtitle
	 * @param node
	 * @param wrapWidth
	 * @return
	 */
	public static HBox makeReverseHBox(String title, String subtitle, Node node, double wrapWidth){
		if (node instanceof Button)
			node.getStyleClass().add("button-splash");
		VBox vb = makeVBox(title, subtitle);
		HBox hb = new HBox(AuthRes.getInt("HBPadding"));
		hb.setAlignment(Pos.CENTER_LEFT);
		hb.getChildren().addAll(vb, node);
		hb.setMaxWidth(wrapWidth);
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
	 * Used to make an Icon button where the css is "button-event"
	 * @param title
	 * @param iv
	 * @param handler
	 * @return
	 */
	public static Button makeIconButton(String title, ImageView iv, EventHandler<ActionEvent> handler){
		Button b = new Button(title, iv);
		b.setOnAction(handler);
		b.getStyleClass().add("button-event");
		return b;
	}
	
	/**
	 * Used to make remove buttons for entity components
	 * @param title
	 * @param handler
	 * @return
	 */
	public static Button makeRemoveButton(String title, EventHandler<ActionEvent> handler){
		Button b = new Button(title);
		b.getStyleClass().add("button-remove");
		b.setOnAction(handler);
		return b;
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

	/**
	 * Calls makeHBox method from above, but restricts the size of the Node
	 * @param delete_entity
	 * @param o
	 * @param removeButton
	 * @return
	 */
	public static HBox makeLittleHBox(String delete_entity, Object o, Button removeButton) {
		removeButton.setMaxHeight(10);
		HBox ret = makeHBox(delete_entity, (String) o, removeButton);
		ret.setMaxHeight(10);
		return ret;
	}

	/**
	 * Calls makeHBox method from above, but centers the HBox
	 * @param create_entity
	 * @param o
	 * @param button
	 * @param center
	 * @return
	 */
	public static HBox makeHBox(String create_entity, Object o, Button button, Pos center) {
		HBox box = makeHBox(create_entity, (String) o, button);
		box.setAlignment(center);
		return box;
	}
	
	/**
	 * Makes thumbnail button with ImageView on top and text underneath
	 * @param imagePath
	 * @param name
	 * @param handler
	 * @param width
	 * @param height
	 * @return
	 */
	public static Button makeThumbnail(String imagePath, String name, EventHandler<ActionEvent> handler, int width, int height){
		ImageView iv = ImageBuilder.getImageView(imagePath, width, height);
		Button b = new Button(name, iv);
		b.setContentDisplay(ContentDisplay.TOP);
		b.setOnAction(handler);
		return b;
	}
	

}
