package authoring.right_components.EntityComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import authoring.ComponentMenu;
import authoring.ComponentMenuFactory;
import authoring.right_components.BaseComponent;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class EntityComponent extends BaseComponent {

	private List<ComponentMenu> menuList;
	public EntityComponent(){
		menuList = new ArrayList<>();
	}

	@Override
	public Pane getView() {
        VBox box = buildBasicView("Entity Creator");        
		box.getChildren().add(getStack());
		//What's the purpose of the .collect function?
		getButtonArray().stream().map((button) -> box.getChildren().add(button)).collect(Collectors.toList());
	    return box;
	}
	

	public Node getStack() {
		StackPane pane = new StackPane();
		pane.getChildren().add(ImageBuilder.getImageView("jen.png", 200, 200));
		return pane;
	}

	public List<Node> getButtonArray() {
		ArrayList<Node> list = new ArrayList<>();
		list.add(ButtonFactory.makeHBox("New Sprite", "Upload a New Image"));
		//list.add(ButtonFactory.makeHBox("Add Behavior", "Attach an Event to this Entity"));
		list.addAll(makeMenuList());
		list.add(ButtonFactory.makeHBox("Print component", "",
				ButtonFactory.makeButton(event -> printComponents())));
		return list;
	}

	private void printComponents() {
		menuList.stream().forEach(x -> System.out.println(x.makeComponent()));
	}

	private Collection<? extends Node> makeMenuList() {
		menuList.add(new ComponentMenuFactory().newComponentMenu("Collidable"));
		List<Node> list;
		list = menuList.stream().map(x -> x.getNode()).collect(Collectors.toList());
		return list;
	}

}
