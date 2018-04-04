package authoring;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import observables.Listener;
import observables.Subject;
import resources.keys.AuthRes;

//Left Pane
public class NavigationPane extends StackPane implements Subject, GUIComponent {

	private ArrayList<String> menuTitles = new ArrayList<String>(Arrays.asList("Entity Creator", "Actions and Events", "Level Preferences", "Storyboard"));
	private ArrayList<String> icons = new ArrayList<String>(Arrays.asList("entity", "event", "level", "story"));
	
	public NavigationPane() {
		this.getStyleClass().add("pane-back");
		initializeButtons();
	}

	@Override
	public void addListener(Listener l) {
		myListeners.add(l);
	}

	@Override
	public void removeListener(Listener l) {
		myListeners.remove(l);
	}

	@Override
	public void notifyListeners(String key) {
		myListeners.stream()
			.forEach(l -> l.update(key));
	}

	private void initializeButtons() {
		VBox navOptions = new VBox();
		Subject np = this;
		for(int i = 0; i < menuTitles.size(); i++) {
			String s = menuTitles.get(i);
			ImageView iv = new ImageView(new Image(AuthRes.getString(icons.get(i))));
			//will be done by image editing class
			iv.setFitHeight(20);
			iv.setFitWidth(20);
			Button b = new Button(s, iv);
			b.getStyleClass().add("button-nav");
			b.setOnAction(e -> np.notifyListeners(s));
			navOptions.getChildren().add(b);
		}
		this.getChildren().add(navOptions);		
	}

	@Override
	public Pane getView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
