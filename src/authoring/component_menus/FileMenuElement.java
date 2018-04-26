package authoring.component_menus;

import frontend_utilities.ButtonFactory;
import frontend_utilities.ImageBuilder;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class FileMenuElement extends MenuElement{
	private final String title;
	private ImageView image;
	private HBox view;
	private FileChooser fileChooser;
	private TextField field;
	public FileMenuElement(String s, Component component) {
		super.setMyComponent(component);
		this.title = s;
		field = new TextField();
		field.setEditable(false);
		field.setText(component.getValue().toString());
		field.setPrefHeight(10);
		field.setPrefWidth(field.getText().toString().length() * 10 + 20 );
		fileChooser = new FileChooser();
		field.setOnMousePressed(e -> updateComponent(KeyCode.SPACE, title));
		view = ButtonFactory.makeHBox(title, null, field);
		image = ImageBuilder.getImageView(field.getText(), 10, 10);
		//view.getChildren().add(image);
	}

	@Override
	public Node getView() {
		return view;
	}

	@Override
	public String getValue() {
		return field.getText();
	}

	@Override
	public void setValue(Object o) {
		field.setText("Hello");
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void updateComponent(KeyCode code, String text) {
		File file = fileChooser.showOpenDialog(new Stage());
		field.setText(file.getName());
		myComponent.setValue(field.getText());
		//image = ImageBuilder.getImageView(field.getText(), 10, 10);
		myMenu.alert();
	}

	@Override
	public void setComponentValue() {
		myComponent.setValue(field.getText());
	}
}
