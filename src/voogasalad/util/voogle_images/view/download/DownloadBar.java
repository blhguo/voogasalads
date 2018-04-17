package voogasalad.util.voogle_images.view.download;

import java.io.File;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * Represents the portion of the UI where Downloads are displayed so that the user may open them and
 * view their old downloads, similar to how the Chrome browser manages downloads.
 * 
 * @author benhubsch
 */
public class DownloadBar {

	private static final int TRANSITION_LENGTH_MILLI = 1000;

	private HBox myHBox;
	private ResourceBundle myResources;

	/**
	 * Creates the DownloadBar object.
	 * 
	 * @param resources A properties file containing key-value language mappings.
	 */
	public DownloadBar(ResourceBundle resources) {
		myResources = resources;

		myHBox = new HBox();
		myHBox.setId("download-bar");
	}

	/**
	 * Gets the NavigationBar's highest level Node for rendering purposes.
	 * 
	 * @return Node to be attached to the scene.
	 */
	public Node getNode() {
		return myHBox;
	}

	/**
	 * Called when the user successfully downloads a new image from within the browser, it adds the image
	 * to the DownloadBar.
	 * 
	 * @param file The file describing the downloaded image.
	 */
	public void addDownload(File file) {
		Download download = new Download(file, myResources);
		download.setCloseHandler(e -> removeDownload(download));
		addTransition(download.getNode());

		myHBox.getChildren().add(download.getNode());
	}

	/**
	 * Called whenever the user closes the Download object, it removes the image from the DownloadBar.
	 * 
	 * @param download
	 */
	public void removeDownload(Download download) {
		myHBox.getChildren().remove(download.getNode());
	}

	/**
	 * Adds a slight fade effect to the Download when it is first initialized to add some character to
	 * the application.
	 * 
	 * @param node The node describing the Download object to which the transition will be applied.
	 */
	private void addTransition(Node node) {
		FadeTransition ft = new FadeTransition(Duration.millis(TRANSITION_LENGTH_MILLI), node);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}
}
