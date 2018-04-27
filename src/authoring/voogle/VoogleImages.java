package authoring.voogle;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import resources.keys.AuthRes;
import voogasalad.util.voogle_images.ImageObserver;
import voogasalad.util.voogle_images.view.NavigationBar;
import voogasalad.util.voogle_images.view.download.DownloadBar;

/**
 * This utility provides a way for a user of an application to download images from the internet for
 * use within their game authoring environment.
 *
 * It removes the hassle of having to manually insert images into the project folder or be limited
 * to the default options only.
 * 
 * Some inspiration was taken from an example browser showed in class and worked on by Owen
 * Astrachan, Marcin Dobosz, Yuzhang Han, Edwin Ward, and Robert C. Duvall, but there are few
 * similarities in the code itself. The methods next(), back(), and home() were included in the
 * example API, but the implementation is distinctly different. The method showError() is the same.
 *
 * @author Ben Hubsch
 */
public class VoogleImages {
	/**
	 * Yahoo! Images was chosen because it has the option to "View Image" which allows this application
	 * to be able to recognize a certain URL as a URL describing an image or not. In theory you could
	 * try changing this to a different website, but in my experience, Yahoo! Images is hands down the
	 * best choice here.
	 */
	public static final String DEFAULT_START_PAGE = "http://images.search.yahoo.com/";
	public static final Dimension2D DEFAULT_SIZE = new Dimension2D(1100, 700);
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.keys/";
	public static final String STYLESHEET = "voogle.css";
	public static final String LANGUAGE = "English";

	/**
	 * Edit this variable if you would like to change the default save directory for the FileChooser.
	 */
	private static final File DEFAULT_SAVE_DIR = new File(System.getProperty("user.dir"));

	private Scene myScene;
	private WebView myPage;
	private ResourceBundle myResources;
	private NavigationBar myNavBar;
	private DownloadBar myDownloadBar;
	private FileChooser myFileChooser;
	private ImageObserver myImageObserver;
	private Stage stage;

	/**
	 * Creates the view of the browser object.
	 * 
	 * @param imageObserver This is the type of the interface that classes that make use of this utility
	 *        must implement.
	 */
	public VoogleImages(ImageObserver imageObserver) {
		myImageObserver = imageObserver;
		myResources = AuthRes.VOOGLE;
		myNavBar = new NavigationBar(e -> back(), e -> next(), e -> home(), e -> download());
		myDownloadBar = new DownloadBar(myResources);
		makeFileChooser();

		BorderPane root = new BorderPane();
		root.setCenter(makePageDisplay());
		root.setTop(myNavBar.getNode());
		root.setBottom(myDownloadBar.getNode());

		myScene = new Scene(root, DEFAULT_SIZE.getWidth(), DEFAULT_SIZE.getHeight());
		myScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);

	}

	/**
	 * Launches the browser, and will likely be called as the result of some event in the calling code
	 * like a button click.
	 */
	public void go() {
		stage = new Stage();
		stage.setTitle(myResources.getString("AppTitle"));
		stage.setScene(myScene);
		stage.show();
	}

	/**
	 * Creates the FileChooser object that is used to save images to the user's local workspace.
	 */
	private void makeFileChooser() {
		myFileChooser = new FileChooser();
		myFileChooser.setTitle(myResources.getString("FileChooserTitle"));
		myFileChooser.setInitialDirectory(DEFAULT_SAVE_DIR);
	}

	/**
	 * Display given message as an error in the GUI.
	 * 
	 * @param message Error message to be displayed to the user.
	 */
	private void showError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(myResources.getString("ErrorTitle"));
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * Moves the user to the default home page of the application.
	 */
	private void home() {
		myPage.getEngine().load(VoogleImages.DEFAULT_START_PAGE);
	}

	/**
	 * Moves the user to the next URL in their web history.
	 */
	private void next() {
		navigate(1);
	}

	/**
	 * Moves the user to the previous URL in their web history.
	 */
	private void back() {
		navigate(-1);
	}

	/**
	 * Moves the user to the appropriate part of their web history.
	 * 
	 * @param direction Specifies the direction and magnitude of the history navigation.
	 */
	private void navigate(int direction) {
		WebHistory history = myPage.getEngine().getHistory();
		try {
			history.go(direction);
		} catch (IndexOutOfBoundsException e) {
			showError(myResources.getString("EndOfHistory"));
		}
	}

	/**
	 * Initializes the WebView object itself and adds a listener to it that fires whenever the user
	 * changes URLs.
	 * 
	 * @return Node to attach to the scene.
	 */
	private Node makePageDisplay() {
		myPage = new WebView();
		myPage.getEngine().getLoadWorker().stateProperty()
				.addListener((ov, oldState, newState) -> handleUrlChange(newState));
		home();
		return myPage;
	}

	/**
	 * Called whenever the Engine detects a URL change, it asks the Navigation Bar to appropriately
	 * display if the URL describes an image or not.
	 * 
	 * @param newState The state of the worker thread completing the URL request (e.g., RUNNING,
	 *        SCHEDULED, SUCCEEDED, READY, FAILED, OR CANCELED).
	 */
	private void handleUrlChange(State newState) {
		if (newState == Worker.State.SUCCEEDED) {
			String href = myPage.getEngine().getLocation();
			myNavBar.toggleDownload(href);
		}
	}

	/**
	 * Downloads the image that can be found at the user's current URL location. Catches exceptions
	 * throughout the download process and displays them to the user.
	 */
	private void download() {
		String href = myPage.getEngine().getLocation();
		try {
			byte[] image = urlToBytes(href);

			File imageFile = myFileChooser.showSaveDialog(myScene.getWindow());
			if (imageFile != null) {
				writeToFile(imageFile, image);

				myDownloadBar.addDownload(imageFile);
				myImageObserver.update(imageFile);
				stage.close();
			}
		} catch (Exception e) {
			showError(myResources.getString("DownloadError"));
		}
	}

	/**
	 * Converts a given URL to an array of bytes.
	 * 
	 * @param href URL from which to fetch bytes.
	 * @return Byte array describing the downloaded image.
	 * @throws Exception
	 */
	private byte[] urlToBytes(String href) throws Exception {
		URL url = new URL(href);
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1 != (n = in.read(buf))) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}

	/**
	 * Writes the byte array describing the downloaded image into the File object.
	 * 
	 * @param imageFile
	 * @param image Byte array describing the downloaded image.
	 * @throws Exception
	 */
	private void writeToFile(File imageFile, byte[] image) throws Exception {
		FileOutputStream fos = new FileOutputStream(imageFile);
		fos.write(image);
		fos.close();
	}
}
