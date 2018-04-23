package voogasalad.util.voogle_images.view;

import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import voogasalad.util.voogle_images.VoogleImages;

/**
 * Represents the bar at the top of the UI that the user can use to navigate through the browser and
 * download an image.
 * 
 * @author benhubsch
 */
public class NavigationBar {

	private static final int BUTTON_HEIGHT = 20;
	private static final String IMAGES = "Image";
	private static final String IMAGEFILE_SUFFIXES = String.format(".*\\.(%s)",
			String.join("|", ImageIO.getReaderFileSuffixes()));

	private Button myDownloadButton;
	private Button myInvalidButton;
	private VBox myResult;
	private HBox myDownloadBox;

	/**
	 * Creates the view of the navigation bar object.
	 * 
	 * @param back A handler for when the user clicks the back button.
	 * @param next A handler for when the user clicks the next button.
	 * @param search A handler for when the user clicks the search button.
	 * @param download A handler for when the user clicks the download button.
	 */
	public NavigationBar(EventHandler<MouseEvent> back, EventHandler<MouseEvent> next, EventHandler<MouseEvent> search,
			EventHandler<MouseEvent> download) {
		myResult = new VBox();
		myResult.getChildren().addAll(makeNavigationPanel(back, next, search, download));
	}

	/**
	 * Gets the NavigationBar's highest level Node for rendering purposes.
	 * 
	 * @return Node to be attached to the scene.
	 */
	public Node getNode() {
		return myResult;
	}

	/**
	 * Called to let the user know whether or not their current URL describes an image and, therefore,
	 * is downloadable.
	 * 
	 * @param urlText User's current URL.
	 */
	public void toggleDownload(String urlText) {
		boolean isImage = urlText.matches(IMAGEFILE_SUFFIXES);
		myDownloadBox.getChildren().clear();
		if (isImage) {
			myDownloadBox.getChildren().add(myDownloadButton);
		} else {
			myDownloadBox.getChildren().add(myInvalidButton);
		}
	}

	/**
	 * 
	 * @param back A handler for when the user clicks the back button.
	 * @param next A handler for when the user clicks the next button.
	 * @param search A handler for when the user clicks the search button.
	 * @param download A handler for when the user clicks the download button.
	 * @return Node to attach to the scene.
	 */
	private Node makeNavigationPanel(EventHandler<MouseEvent> back, EventHandler<MouseEvent> next,
			EventHandler<MouseEvent> search, EventHandler<MouseEvent> download) {
		ResourceBundle imageBundle = ResourceBundle.getBundle(VoogleImages.DEFAULT_RESOURCE_PACKAGE + IMAGES);
		HBox result = new HBox();
		result.setId("nav-panel");

		HBox left = new HBox();
		left.setId("left-nav");
		Button backButton = makeButton(imageBundle.getString("Back"), back);
		Button nextButton = makeButton(imageBundle.getString("Next"), next);
		Button homeButton = makeButton(imageBundle.getString("Search"), search);
		left.getChildren().addAll(backButton, nextButton, homeButton);

		Region spacing = new Region();
		HBox.setHgrow(spacing, Priority.ALWAYS);

		myDownloadBox = new HBox();
		myDownloadBox.setId("right-nav");
		myDownloadButton = makeButton(imageBundle.getString("Download"), download);
		myInvalidButton = makeButton(imageBundle.getString("InvalidDownload"), null);
		myDownloadBox.getChildren().addAll(myInvalidButton);

		result.getChildren().addAll(left, spacing, myDownloadBox);
		return result;
	}

	/**
	 * Helper function called to make uniform buttons for the NavigationBar.
	 * 
	 * @param filename Name of the image to attach to the Button.
	 * @param handler Handler that describes action of program on click.
	 * @return Button The created Button object.
	 */
	private Button makeButton(String filename, EventHandler<MouseEvent> handler) {
		Button button = new Button();
		button.setOnMouseClicked(handler);

		ImageView imageView = new ImageView(VoogleImages.DEFAULT_RESOURCE_PACKAGE + filename);
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(BUTTON_HEIGHT);

		button.setGraphic(imageView);
		return button;
	}
}
