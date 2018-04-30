package authoring.component_menus;

import java.io.File;

import authoring.voogle.VoogleApp;
import frontend_utilities.ButtonFactory;
import frontend_utilities.ImageBuilder;
import game_engine.Component;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import resources.keys.AuthRes;


public class FileMenuElement extends MenuElement{
	private final String title;
	private ImageView image;
	private VBox view;
	private FileChooser fileChooser;
	private TextField field;
	public FileMenuElement(String s, Component component) {
		super.setMyComponent(component);
		this.title = s;
		field = new TextField();
		field.setStyle("-fx-cursor: hand;");
		field.setEditable(false);
		field.setText(component.getValue().toString());
		field.setPrefHeight(10);
		field.setPrefWidth(field.getText().toString().length() * 10 + 20 );
		fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File("./images"));
		field.setOnMousePressed(e -> updateComponent(KeyCode.SPACE, title, true));
		view = new VBox();
		view.getChildren().add(ButtonFactory.makeReverseHBox(title, null, field, AuthRes.getInt("MenuElementWidth")));
		image = ImageBuilder.getImageView(field.getText(), 10, 10);
		//view.getChildren().add(image);
		view.getChildren().add(getVoogleButton());
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
	public void updateComponent(KeyCode code, String text, boolean alert) {
		File file = fileChooser.showOpenDialog(new Stage());
		field.setText(file.getName());
		myComponent.setValue(field.getText());
		//image = ImageBuilder.getImageView(field.getText(), 10, 10);
		if (alert) myMenu.alert();
	}
	public void voogle(){
		VoogleApp app = new VoogleApp(this);
		

	}
	@Override
	public void setComponentValue() {
		myComponent.setValue(field.getText());
	}

	public Node getVoogleButton() {
		Button voogleButton = ButtonFactory.makeButton(e -> voogle());
		voogleButton.setText("Voogle");
		Tooltip tip = new Tooltip("Search for Image with VoogleImages");
		voogleButton.setTooltip(new Tooltip("Search for Image with VoogleImages"));
		return voogleButton;
	}

	public void update(File file){
		field.setText(file.getName());
		setComponentValue();
	}

}
