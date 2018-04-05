package authoring;

import java.util.ArrayList;
import java.util.Arrays;
import authoring.utilities.ButtonFactory;
import authoring.utilities.ImageBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import observables.Listener;
import observables.Subject;
import resources.keys.AuthRes;

//Left Pane
public class NavigationPane extends GridPane implements Subject, GUIComponent {

	private ArrayList<String> menuTitles = new ArrayList<String>(Arrays.asList("Entity Creator", "Actions and Events", "Level Preferences", "Storyboard"));
	private ArrayList<String> compIcons = new ArrayList<String>(Arrays.asList("entity", "event", "level", "story"));
	private ArrayList<String> prefTitles = new ArrayList<String>(Arrays.asList("Play Game", "Save Game", "Settings"));
	private ArrayList<String> prefIcons = new ArrayList<String>(Arrays.asList("play", "save", "settings"));
	
	public NavigationPane() {
		this.getStyleClass().add("pane-back");
		this.setHgap(AuthRes.getInt("Padding"));
		this.setVgap(AuthRes.getInt("Padding"));
		this.setPadding(new Insets(AuthRes.getInt("Padding")));
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
		VBox navOptions = new VBox(AuthRes.getInt("NavPadding"));
		Subject np = this;
		for(int i = 0; i < menuTitles.size(); i++) {
			String s = menuTitles.get(i);
			ImageView iv = new ImageView(new Image(AuthRes.getString(compIcons.get(i))));
			//will be done by image editing class
			iv = ImageBuilder.resize(iv, 20, 20);
			Button b = ButtonFactory.makeButton(s, iv, e -> np.notifyListeners(s),
					"button-nav");
			navOptions.getChildren().add(b);
		}
		this.add(navOptions, 0, 20);	
		
		VBox prefButtons = new VBox(AuthRes.getInt("NavPadding"));
		for (int i = 0; i < prefTitles.size(); i++){
			Button b = makeButton(prefTitles.get(i), AuthRes.getString(prefIcons.get(i)));
			prefButtons.getChildren().add(b);
		}
		this.add(prefButtons, 0, 85);
	}
	
	private Button makeButton(String label, String ivPath){
		// from image editing class
		ImageView iv = new ImageView(new Image(ivPath));
		iv.setFitHeight(20);
		iv.setFitWidth(20);
		Button b = new Button(label, iv);
		b.getStyleClass().add("button-nav");
		return b;
	}

	@Override
	public Pane getView() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
