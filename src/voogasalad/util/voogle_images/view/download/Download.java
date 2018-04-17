package voogasalad.util.voogle_images.view.download;

import java.awt.Desktop;
import java.io.File;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * Represents a downloaded image object.
 * 
 * @author benhubsch
 */
public class Download {

	private static final int MAX_NAME_LENGTH = 20;

	private HBox myHBox;
	private Button myClose;

	/**
	 * Creates a Download object.
	 * 
	 * @param file The file describing the downloaded image.
	 * @param resources A properties file containing key-value language mappings.
	 */
	public Download(File file, ResourceBundle resources) {
		myHBox = new HBox();
		myHBox.setId("download");

		Button open = new Button(format(file.getName()));
		open.getStyleClass().add("open");
		open.setOnMouseClicked(e -> openDesktop(file, resources));

		myClose = new Button("X");
		myClose.getStyleClass().add("close");

		myHBox.getChildren().addAll(open, myClose);
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
	 * Adds a handler to the Button object that is triggered when the Download object is closed.
	 * 
	 * @param handler The action to be completed on close.
	 */
	public void setCloseHandler(EventHandler<MouseEvent> handler) {
		myClose.setOnMouseClicked(handler);
	}

	/**
	 * Formats the name of the downloaded image object -- if the name exceeds a certain length, it will
	 * be truncated appropriately.
	 * 
	 * @param name Name of the Downloaded file.
	 * @return Name formatted for display purposes.
	 */
	private String format(String name) {
		return name.length() > MAX_NAME_LENGTH ? name.substring(0, MAX_NAME_LENGTH - 3) + "..." : name;
	}

	/**
	 * Called to open the user's image within their native OS when the click on the Download object, and
	 * displays an error message if not possible.
	 * 
	 * @param file The file describing the downloaded image.
	 * @param resources A properties file containing key-value language mappings.
	 */
	private void openDesktop(File file, ResourceBundle resources) {
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
				return;
			} else {
				throw new DesktopNotSupportedException(resources.getString("DesktopException"));
			}
		} catch (DesktopNotSupportedException dnse) {
			showError(resources.getString("DesktopIncompatible"), dnse.getMessage());
		} catch (Exception e) {
			showError(resources.getString("FileError"), resources.getString("FileException"));
		}
	}

	/**
	 * Display given message as an error in the GUI.
	 * 
	 * @param title Title of Error box to be displayed to user.
	 * @param content Content of Error message to be displayed to user.
	 */
	private void showError(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
