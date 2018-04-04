package authoring.right_components;

import authoring.GUIComponent;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import resources.keys.AuthRes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static authoring.utilities.ButtonFactory.makeHBox;

public class EntityComponent implements GUIComponent {

	private int SIZE = 300;

	@Override
	public Pane getView() {
        VBox box = getVBox();
        //setBackground(box);
        box.getStyleClass().add("pane-back");
        box.getChildren().add(getTitle());
        box.getChildren().add(getSeparator());
		box.getChildren().add(getStack());
		//What's the purpose of the .collect function?
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
	    return box;
	}
    public VBox getVBox() {
        VBox box = new VBox();
        box.setPrefWidth(SIZE);
        //box.setAlignment(Pos.CENTER_LEFT);
        box.setPadding(new Insets(20, 30, 20 ,30));
        box.setSpacing(20);
        return box;
    }
    private void setBackground(VBox box){
//        BackgroundImage back = new BackgroundImage(new Image("background.png"), BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        box.setBackground(new Background(back));
    }
    private Node getTitle() {
        Label ret = new Label("Entity Creator");
        ret.getStyleClass().add("pane-title");
        return ret;
    }

	public Node getSeparator() {
		Separator s = new Separator();
		s.setHalignment(HPos.CENTER);
		s.setPrefWidth(SIZE * 2.0/3);
		return s;
	}

	public Node getStack() {
		StackPane pane = new StackPane();
		pane.getChildren().add(ImageBuilder.getImageView("jen.png", 200, 200));
		return pane;
	}

	public List<HBox> getButtonArray() {
		ArrayList<HBox> list = new ArrayList<>();
		list.add(ButtonFactory.makeHBox("New Sprite", "Upload a New Image"));
		list.add(ButtonFactory.makeHBox("Add Behavior", "Attach an Event to this Entity"));


		return list;
	}

}
