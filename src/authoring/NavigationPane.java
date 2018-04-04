package authoring;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import observables.Listener;
import observables.Subject;

//Left Pane
public class NavigationPane extends StackPane implements Subject {

	private ArrayList<String> menuTitles = new ArrayList<String>(Arrays.asList("Entity Creator", "Actions and Events", "Level Preferences", "Storyboard"));
	
	public NavigationPane() {
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
		for(String s:menuTitles) {
			Button b = new Button(s);
			b.setOnAction(e -> np.notifyListeners(s));
			navOptions.getChildren().add(b);
		}
		this.getChildren().add(navOptions);		
	}
	
	
}
