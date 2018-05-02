package authoring.voogle;

import java.io.File;

import authoring.component_menus.FileMenuElement;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import voogasalad.util.voogleimages.ImageObserver;
import voogasalad.util.voogleimages.VoogleImages;

public class VoogleApp implements ImageObserver {

	private Scene myScene;
	private VBox myCol;
	private FileMenuElement myFileMenuElement;

	public VoogleApp(FileMenuElement element) {
		myFileMenuElement = element;
		myCol = new VBox();
		VBox main = new VBox();
		VoogleImages voogleImages = new VoogleImages(this);
//		main.getChildren().add(new Label("Voogle Images"));
//		main.getChildren().add(createMenu(e -> voogleImages.go()));
		voogleImages.go();
		myScene = new Scene(main);
	}
	public Scene getMyScene(){
		return myScene;
	}
	private Node createMenu(EventHandler<ActionEvent> handler) {
		VBox vbox = new VBox();
		vbox.setId("vbox");
		Button launch = new Button("Select an Image");
		launch.setOnAction(handler);

		myCol = new VBox();
		myCol.setId("vbox");
		ScrollPane sp = new ScrollPane();
		sp.setContent(myCol);
		vbox.getChildren().addAll(launch, sp);
		return vbox;
	}

	//@Override
	public void update(File file) {
		myFileMenuElement.update(file);
	}
}
