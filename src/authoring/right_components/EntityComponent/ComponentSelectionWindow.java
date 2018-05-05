package authoring.right_components.EntityComponent;

import java.util.ArrayList;
import java.util.List;

import authoring.component_menus.ComponentMenu;
import frontend_utilities.ButtonFactory;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.keys.AuthRes;

// should this class extend guiBuilder?
public class ComponentSelectionWindow {
	private EntityPane myPane;
	private EntityWrapper wrapper;
	private List<ComponentMenu> activeMenus;
	private Stage myStage;
	private Stage primaryStage;
	public ComponentSelectionWindow(EntityWrapper wrapper, EntityPane pane, Stage s) {
		this.wrapper = wrapper;
		activeMenus = new ArrayList<>();
		myStage = null;
		myPane = pane;
		primaryStage = s;
	}
	public void display(){
		myStage = new Stage();
		myStage.initOwner(primaryStage);
		myStage.setFullScreen(false);
		VBox displayBox = getDisplay();
		Scene scene = new Scene(displayBox);
		scene.getStylesheets().add(getClass().getResource("/main/aesthetic.css").toString());
		myStage.setScene(scene);
		myStage.show();
	}
	public VBox getDisplay() {
		VBox master = new VBox(AuthRes.getInt("Padding"));
		master.setPadding(new Insets(AuthRes.getInt("HBPadding")));
		master.getStyleClass().add("comp-back");
		master.setAlignment(Pos.CENTER);
		Text title = new Text("Available Components");
		title.getStyleClass().add("pane-title");
		master.getChildren().add(title);
		
		Separator line = new Separator();
		line.setHalignment(HPos.CENTER);
		master.getChildren().add(line);
		master.getChildren().add(getTilePanes());
		master.getChildren().add(getButton());
		return master;
	}
	private VBox getTilePanes() {
		VBox wrap= new VBox();
		//wrap.getStyleClass().add("pane-back");
		
		//wrap.getChildren().add(title);
		VBox labelList = new VBox();
		labelList.setSpacing(AuthRes.getInt("Padding"));
		for (ComponentMenu menu : wrapper.getMenuList()){
			if (!menu.isIncluded()) {
				labelList.getChildren().add(generateLabel(menu.getType(), menu));
			}
		}
		ScrollPane pane = new ScrollPane(labelList);
		pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		//pane.getStyleClass().add("pane-back");
		wrap.getChildren().add(pane);
		return wrap;
	}

	private Label generateLabel(String type, ComponentMenu menu) {
		Label ret = new Label(" - " + type);
//		String style = "-fx-border-color: white; -fx-border-width: 3;";
//		ret.setStyle(style + "-fx-background-color: cornflowerblue");
		ret.getStyleClass().add("comp-label");

		ret.prefWidthProperty().bind(myStage.widthProperty());
		ret.setOnMouseClicked(e -> {
			if (!activeMenus.contains(menu)) {
				activeMenus.add(menu);
				//ret.setStyle(style + "-fx-background-color: lightblue");
				ret.setStyle("-fx-effect: dropshadow(gaussian, #338099, 10, 0.5, 0, 0)");
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
		return ButtonFactory.makeHBox("Add selected component", null,
				ButtonFactory.makeButton(e -> {
					activeMenus.stream().forEach(a -> a.Include());
					unDisplay();
				}));
	}

	private void unDisplay() {
		myStage.close();
		wrapper.addAllComponents(wrapper.getEntity());
		myPane.refresh();
	}
}
