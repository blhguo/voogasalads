package voogasalad.example.voogle_images;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import voogasalad.util.voogle_images.ImageObserver;
import voogasalad.util.voogle_images.VoogleImages;

/**
 * A simple demo application demonstrating how to make use of the VoogleImages utility.
 * 
 * @author benhubsch
 */
public class DemoApp implements ImageObserver {

	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final String DEFAULT_LANG = "English";
	private static final String STYLESHEET = "example.css";
	private static final Dimension2D DIMENSION = new Dimension2D(300, 800);
	private static final int CONTENT_WIDTH = 250;
	private static final int EXAMPLE_IMAGE_HEIGHT = 100;

	private Scene myScene;
	private VBox myCol;
	private ResourceBundle myResources;

	/**
	 * Creates the demo application.
	 */
	public DemoApp() {
		VBox main = new VBox();
		main.setId("vbox");

		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + DEFAULT_LANG);

		VoogleImages voogleImages = new VoogleImages(this);

		main.getChildren().add(createIntro());
		main.getChildren().add(createMenu(e -> voogleImages.go()));

		myScene = new Scene(main, DIMENSION.getWidth(), DIMENSION.getHeight());
		myScene.getStylesheets().add(DEFAULT_RESOURCE_PACKAGE + STYLESHEET);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see voogle_images.ImageObserver#update(java.io.File)
	 */
	@Override
	public void update(File file) {
		HBox wrapper = new HBox();
		wrapper.setId("wrapper");

		ImageView imageView = createBoundedImage(file);
		ImageView hr = createBoundedImage(DEFAULT_RESOURCE_PACKAGE + "hr.png");
		hr.setFitWidth(CONTENT_WIDTH);

		wrapper.getChildren().add(imageView);
		myCol.getChildren().addAll(wrapper, hr);
	}

	/**
	 * Creates an Image bounded by certain width or height.
	 * 
	 * @param filename The filename describing an image.
	 * @return ImageView representing the manipulated image.
	 */
	private ImageView createBoundedImage(String filename) {
		Image image = new Image(filename);
		return imageToView(image);
	}

	/**
	 * Creates an Image bounded by certain width or height.
	 * 
	 * @param File The file describing an image.
	 * @return ImageView representing the manipulated image.
	 */
	private ImageView createBoundedImage(File file) {
		try {
			Image image = new Image(new FileInputStream(file));
			return imageToView(image);
		} catch (FileNotFoundException fnf) {
			return null;
		}
	}

	/**
	 * Helper function that assists in the process of resizing an image to fit within the UI.
	 * 
	 * @param image Image to be converted to an ImageView.
	 * @return Converted ImageView.
	 */
	private ImageView imageToView(Image image) {
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		if (imageView.getBoundsInLocal().getWidth() > imageView.getBoundsInLocal().getHeight()) {
			imageView.setFitWidth(EXAMPLE_IMAGE_HEIGHT);
		} else {
			imageView.setFitHeight(EXAMPLE_IMAGE_HEIGHT);
		}
		return imageView;
	}

	/**
	 * Creates the introductory portion of the DemoApp that explains the utility of VoogleImages.
	 * 
	 * @return Node The Node representing the introduction object to render on screen.
	 */
	private Node createIntro() {
		VBox vbox = new VBox();
		vbox.setId("intro");

		Image image = new Image(DEFAULT_RESOURCE_PACKAGE + "voogle.png");
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(EXAMPLE_IMAGE_HEIGHT);

		Text welcome = new Text(myResources.getString("Intro"));
		welcome.setWrappingWidth(CONTENT_WIDTH);

		Text menu = new Text(myResources.getString("Menu"));
		menu.setWrappingWidth(CONTENT_WIDTH);

		Text support = new Text(myResources.getString("Support"));
		support.setWrappingWidth(CONTENT_WIDTH);

		vbox.getChildren().addAll(imageView, welcome, menu, support);
		return vbox;
	}

	/**
	 * Creates a rough sketch of a VOOGASalad menu to simulate potential use cases.
	 * 
	 * @param handler Called to launch to VoogleImages utility on click.
	 * @return Node representing the menu that can be rendered on screen.
	 */
	private Node createMenu(EventHandler<ActionEvent> handler) {
		VBox vbox = new VBox();
		vbox.setId("vbox");
		Button launch = new Button(myResources.getString("Select"));
		launch.setOnAction(handler);

		myCol = new VBox();
		myCol.setId("vbox");
		ScrollPane sp = new ScrollPane();
		sp.setContent(myCol);
		vbox.getChildren().addAll(launch, sp);
		return vbox;
	}

	/**
	 * Gets the Scene object.
	 *
	 * @return Scene
	 */
	public Scene getScene() {
		return myScene;
	}
}
