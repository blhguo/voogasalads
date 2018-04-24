package authoring.right_components.EntityComponent;

import authoring.component_menus.ComponentMenu;
import frontend_utilities.ButtonFactory;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ComponentSelectionWindow {
	private EntityPane myPane;
	private EntityWrapper wrapper;
	private List<ComponentMenu> activeMenus;
	private Stage myStage;
	public ComponentSelectionWindow(EntityWrapper wrapper, EntityPane pane) {
		this.wrapper = wrapper;
		activeMenus = new ArrayList<>();
		myStage = null;
		myPane = pane;
	}
	public void display(){
		myStage = new Stage();
		VBox displayBox = getDisplay();
		Scene scene = new Scene(displayBox);
		myStage.setScene(scene);
		myStage.show();
	}
	public VBox getDisplay() {
		VBox master = new VBox();
		master.setAlignment(Pos.CENTER);
		master.getChildren().add(getTilePanes());
		master.getChildren().add(getButton());
		return master;
	}
	private VBox getTilePanes() {
		VBox wrap= new VBox();
		VBox labelList = new VBox();
		labelList.setSpacing(5);
		for (ComponentMenu menu : wrapper.getMenuList()){
			if (!menu.isIncluded())
				labelList.getChildren().add(generateLabel(menu.getType(), menu));
		}
		ScrollPane pane = new ScrollPane(labelList);
		pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		wrap.getChildren().add(pane);
		return wrap;
	}

	private Label generateLabel(String type, ComponentMenu menu) {
		Label ret = new Label(type);
		String style = "-fx-border-color: white; -fx-border-width: 3;";
		ret.setStyle(style + "-fx-background-color: cobalt");
		ret.prefWidthProperty().bind(myStage.widthProperty());
		ret.setOnMouseClicked(e -> {
			if (!activeMenus.contains(menu)) {
				activeMenus.add(menu);
				ret.setStyle(style + "-fx-background-color: lightblue");
			}
			else {
				activeMenus.remove(menu);
				ret.setStyle("");
			}
			//ret.setStyle("-fx-background-color: lightblue");
		});
		return ret;
	}

	private Node getButton() {
		HBox box = ButtonFactory.makeHBox("Add selected component", null,
				ButtonFactory.makeButton(e -> {
					activeMenus.stream().forEach(a -> a.Include());
					unDisplay();
				}));
		return box;
	}

	private void unDisplay() {
		myStage.close();
		myPane.refresh();
	}
}
